package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PictureDto;
import softuni.exam.domain.dto.PictureRootDto;
import softuni.exam.domain.entities.PictureEntity;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;
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
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importPictures() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        PictureRootDto pictures = xmlParser
                .parseXml(PictureRootDto.class, PICTURES_XML);

        for (PictureDto picture : pictures.getPictures()) {
            if (!validationUtil.isValid(picture) || findByUrl(picture.getUrl()) != null) {
                sb.append("Invalid picture");
            } else {
                PictureEntity pic = this.modelMapper.map(picture, PictureEntity.class);
                pictureRepository.saveAndFlush(pic);
                sb.append(String.format("Successfully imported picture - %s"
                        , picture.getUrl()));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return Files.readString(Path.of(PICTURES_XML));
    }

    @Override
    public PictureEntity findByUrl(String url) {
        return pictureRepository.findByUrl(url);
    }
}
