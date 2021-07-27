package softuni.exam.service.impl;
import org.springframework.stereotype.Service;
import softuni.exam.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public String importPictures() {
       return "";
    }

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPicturesXmlFile() {
        return "";
    }
}
