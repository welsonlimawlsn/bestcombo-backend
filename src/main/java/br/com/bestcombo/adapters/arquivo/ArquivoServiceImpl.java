package br.com.bestcombo.adapters.arquivo;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.service.ArquivoService;
import br.com.bestcombo.ports.service.ImagemService;

@ApplicationScoped
public class ArquivoServiceImpl implements ArquivoService {

    @Inject
    S3Client s3Client;

    @Inject
    ImagemService imagemService;

    @ConfigProperty(name = "bucket.name")
    String bucketName;

    @Override
    public void upload(byte[] arquivo, String tipo, String nome) {
        if (isImage(tipo)) {
            arquivo = imagemService.comprimirImagem(arquivo, tipo);
        }

        s3Client.putObject(getPutObjectRequest(tipo, nome), getRequestBody(arquivo));
    }

    @Override
    public Map<String, Object> download(String nomeArquivo) throws NegocioException {
        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getGetObjectRequest(nomeArquivo));

            return mapperResponse(nomeArquivo, response);
        } catch (NoSuchKeyException e) {
            throw new NegocioException(Erro.ARQUIVO_NAO_ENCONTRADO);
        }
        catch (IOException e) {
            throw new InfraestruturaException("Ocorreu um erro ao ler os bytes do arquivo vindo do Bucket S3", e);
        }
    }

    private HashMap<String, Object> mapperResponse(String nomeArquivo, ResponseInputStream<GetObjectResponse> object) throws IOException {
        HashMap<String, Object> response = new HashMap<>();

        response.put(ArquivoService.ARQUIVO, object.readAllBytes());
        response.put(ArquivoService.TIPO_ARQUIVO, object.response().contentType());
        response.put(ArquivoService.NOME_ARQUIVO, nomeArquivo);

        return response;
    }

    private boolean isImage(String tipo) {
        return tipo.startsWith("image");
    }

    private GetObjectRequest getGetObjectRequest(String nomeArquivo) {
        return GetObjectRequest.builder().bucket(bucketName).key(nomeArquivo).build();
    }

    private PutObjectRequest getPutObjectRequest(String tipo, String nome) {
        return PutObjectRequest.builder()
                .contentType(tipo)
                .key(nome)
                .bucket(bucketName)
                .build();
    }

    private RequestBody getRequestBody(byte[] arquivo) {
        return RequestBody.fromBytes(arquivo);
    }

}
