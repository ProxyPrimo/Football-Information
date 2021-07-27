package softuni.exam.service.impl;


import org.springframework.stereotype.Service;
import softuni.exam.service.PlayerService;


import javax.transaction.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Override
    public String importPlayers(){
       return "";
    }

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPlayersJsonFile() {
        return "";
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
