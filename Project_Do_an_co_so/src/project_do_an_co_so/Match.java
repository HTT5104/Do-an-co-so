package project_do_an_co_so;

public class Match {
    private String time;
    private String date;
    private String tournament;
    private String team;
    private String stadium;
    private String homeAway;

    public Match(String time, String date, String tournament, String team, String stadium, String homeAway) {
        this.time = time;
        this.date = date;
        this.tournament = tournament;
        this.team = team;
        this.stadium = stadium;
        this.homeAway = homeAway;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setstadium(String stadium) {
        this.stadium = stadium;
    }
    
    public void setHomeAway(String homeAway) {
        this.homeAway = homeAway;
    }
    
    public String getTime(){ return time;}
    public String getDate(){ return date;}
    public String getTournament(){ return tournament;}
    public String getTeam(){ return team;}
    public String getStadium(){ return stadium;}
    public String getHomeAway(){ return homeAway;}

    public String toCSV() {
        return time + "," + date + "," + tournament + "," + team + "," + stadium + "," + homeAway;
    }
}
