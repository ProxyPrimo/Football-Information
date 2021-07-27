package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.TeamDto;
import softuni.exam.domain.dto.TeamRootDto;
import softuni.exam.domain.entities.PictureEntity;
import softuni.exam.domain.entities.TeamEntity;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.PictureService;
import softuni.exam.service.TeamService;
import softuni.exam.util.ValidationUtil;
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
    private final ValidationUtil validationUtil;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository
            , XmlParser xmlParser
            , ValidationUtil validationUtil
            , PictureService pictureService,
                           ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importTeams() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        TeamRootDto teams = xmlParser.parseXml(TeamRootDto.class, FILE_PATH);
        for (TeamDto teamDto : teams.getTeam()) {
            PictureEntity foundPicture = pictureService.findByUrl(teamDto.getPicture().getUrl());
            if (!validationUtil.isValid(teamDto) || foundPicture == null) {
                sb.append("Invalid team");
            } else {
                TeamEntity team = modelMapper.map(teamDto, TeamEntity.class);
                team.setPicture(foundPicture);
                teamRepository.saveAndFlush(team);
                sb.append(team.getName());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
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
