package stibride.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class StopsDto extends Dto<Integer> {

    private int stationKey;
    private String stationName;
    private List<Integer> lines;

    public StopsDto(Integer key) {
        super(key);
    }

    public StopsDto(Integer key, String stationName) {
        super(key);
        this.stationName = stationName;
        lines = new ArrayList<>();
    }

    public StopsDto(Integer key, int stationKey) {
        super(key);
        this.stationKey = stationKey;
        lines = new ArrayList<>();
    }   
    
    public StopsDto(Integer key, int stationKey, List<Integer> lines) {
        super(key);
        this.stationKey = stationKey;
        this.lines = lines;
    }
    
    public StopsDto(Integer key, String stationName, List<Integer> lines) {
        super(key);
        this.stationName = stationName;
        this.lines = lines;
    }

    public void addLine(int line) {
        lines.add(line);
    }

    public List<Integer> getLines() {
        return lines;
    }
    
    public int getLine() {
        return lines.get(0);
    }

    public String getStationName() {
        return stationName;
    }

    public int getStationKey() {
        return stationKey;
    }

}
