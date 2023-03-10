
/**
 * Maman 14
 *
 * @author Or Kubani
 */
public class RentNode
{
    private Rent _rent;
    private RentNode _next;
    
    // Constractors
    /**
     * Constractor that gets an rent object and set it to be the head.
     * Set the next rent to be null.
     * 
     * @param r - set an Rent Object to be the head of the list.
     */
    public RentNode (Rent r)
    {
        _rent = new Rent(r);
        _next = null;
    }
    
    /**
     * Constractor that gets an rent object and set it to be the head.
     * Set the next param - RentNode Object - to be the next rent.
     * 
     * @param r - set an Rent Object to be the head of the list. 
     * @param next - Set the next to be the next Rent. 
     */
    public RentNode (Rent r, RentNode next)
    {
        _rent = new Rent(r);
        _next = next;
    }
    
    /**
     * Copy constractor - get an RentNode and copy the details to a new RentNode
     * 
     * @param - other - RentNode to copy.
     */
    public RentNode (RentNode other)
    {
        _rent = new Rent(other._rent);
        _next = other._next;
    }
    
    // Getters
    
    /**
     * Returns the RentNode value.
     * 
     * @Return the Rent vlaue.
     */
    public Rent getRent()
    {
        return new Rent(_rent);
    }
    
    /**
     * Returns the address of the next RentNode.
     * 
     * @Return the address of the next RentNode
     */
    public RentNode getNext()
    {
        return _next;
    }
    
    // Setters
    /**
     * Gets an Rent object to update the value of the current RentNode
     * 
     * @Param - r - Rent object
     */
    public void setRent(Rent r)
    {
        _rent = new Rent(r);
    }
    
    /**
     * Gets an RentNode and set the next to be it.
     * 
     * @Param - next - RentNode Object
     */
    public void setNext( RentNode next)
    {
        _next = next;
    }
}
