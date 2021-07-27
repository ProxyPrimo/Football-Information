package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.TeamRootDto;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.TeamService;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String FILE_PATH = "src/main/resources/files/xml/teams.xml";

    private final TeamRepository teamRepository;
    private final XmlParser xmlParser;

    public TeamServiceImpl(TeamRepository teamRepository, XmlParser xmlParser) {
        this.teamRepository = teamRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public String importTeams() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        TeamRootDto teams = xmlParser.parseXml(TeamRootDto.class, FILE_PATH);



        return "";
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

}
