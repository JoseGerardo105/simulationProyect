package stibride.dto;

/**
 * A Data Transfer Object that represents a favorite trip
 * 
 * @author
 */
public class FavoriteTripDto extends Dto<Integer> {

    private final String origin;
    private final String destination;

    public FavoriteTripDto(Integer key, String origin, String destination) {
        super(key);
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Get the origin of the favorite trip
     * 
     * @return The origin of the trip.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Get the destination of the favorite trip
     * 
     * @return The destination of the trip.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns a string representation of the trip
     * 
     * @return The origin and destination of the trip.
     */
    @Override
    public String toString() {
        return origin + " -> " + destination;
    }
}
