package stibride.dto;

/**
 *
 * @author
 */
public class StationsDto extends Dto<Integer> {

    private String name;

    public StationsDto(Integer key) {
        super(key);
    }

    public StationsDto(Integer key, String name) {
        super(key);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
