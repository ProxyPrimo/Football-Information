package softuni.exam.service.impl;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PlayerDto;
import softuni.exam.domain.entities.PictureEntity;
import softuni.exam.domain.entities.PlayerEntity;
import softuni.exam.domain.entities.TeamEntity;
import softuni.exam.domain.entities.enumerations.PositionValue;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.service.PictureService;
import softuni.exam.service.PlayerService;
import softuni.exam.service.TeamService;
import softuni.exam.util.ValidationUtil;


import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private static final String FILE_PATH = "src/main/resources/files/json/players.json";

    private final Gson gson;
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PictureService pictureService;
    private final TeamService teamService;

    public PlayerServiceImpl(Gson gson, PlayerRepository playerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, PictureService pictureService, TeamService teamService) {
        this.gson = gson;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.pictureService = pictureService;
        this.teamService = teamService;
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();

        PlayerDto[] players = gson.fromJson(readPlayersJsonFile(), PlayerDto[].class);

        for (PlayerDto player : players) {
            PictureEntity playerPicture = pictureService.findByUrl(player.getPicture().getUrl());

            TeamEntity teamEntity = teamService.findByNameAndByPictureUrl(player.getTeam().getName()
                    , player.getTeam().getPicture().getUrl());

            boolean positionValueFound = Arrays
                    .stream(PositionValue
                            .values()).anyMatch(p ->
                                p.toString().equals(player.getPosition())
                    );


            if (!validationUtil.isValid(player)
                    || playerPicture == null
                    || teamEntity == null
            || !positionValueFound) {
                sb.append("Invalid player");
            } else {
                PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
                playerEntity.setPicture(playerPicture);
                playerEntity.setTeam(teamEntity);
                playerRepository.saveAndFlush(playerEntity);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String exportPlayersWithALargerSalaryThanTheGiven() {
        return "";
    }

    @Override
    public String exportPlayersInATeam() {
        return "";
    }
}
