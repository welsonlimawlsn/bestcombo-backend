package br.com.bestcombo.adapters.arquivo;

import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.ports.service.ImagemService;

@ApplicationScoped
@Slf4j
public class ImagemServiceImpl implements ImagemService {

    @Override
    public byte[] comprimirImagem(byte[] imagem, String tipo) {
        try {
            ImageWriter writer = ImageIO.getImageWritersByMIMEType(tipo).next();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageInputStream imageInputStream = ImageIO.createImageOutputStream(output);

            writer.setOutput(imageInputStream);

            BufferedImage read = ImageIO.read(new ByteArrayInputStream(imagem));
            ImageWriteParam defaultWriteParam = writer.getDefaultWriteParam();

            defaultWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            defaultWriteParam.setCompressionQuality(0.2f);

            writer.write(null, new IIOImage(read, null, null), defaultWriteParam);

            return output.toByteArray();
        } catch (Exception e) {
            throw new InfraestruturaException("Ocorreu um erro durante a compressão da imagem", e);
        }
    }

}
