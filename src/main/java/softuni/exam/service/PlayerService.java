package softuni.exam.service;

public interface PlayerService {
    String importPlayers();

    boolean areImported();

    String readPlayersJsonFile();

    String exportPlayersInATeam();

    String exportPlayersWithALargerSalaryThanTheGiven();
}
