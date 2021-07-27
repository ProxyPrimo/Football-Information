package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PictureDto;
import softuni.exam.domain.dto.PictureRootDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PictureServiceImpl implements PictureService {

    public static final String PICTURES_XML = "src/main/resources/files/xml/pictures.xml";

    private final PictureRepository pictureRepository;
    private final XmlParser xmlParser;

    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public String importPictures() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        PictureRootDto pictures = xmlParser
                .parseXml(PictureRootDto.class, PICTURES_XML);

        return "";
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return Files.readString(Path.of(PICTURES_XML));
    }
}
