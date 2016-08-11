package grapecity.fitnessexplorer.entities;


/**
 * Created by David.Bickford on 5/25/2016.
 */
public class ActivityColor
{
    private String activity;
    private int color;

    public ActivityColor(String activity, int color)
    {
        this.activity = activity;
        this.color = color;
    }

    public void setActivity(String activity)
    {
        this.activity = activity;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public String getActivity()
    {
        return activity;
    }

    public int getColor()
    {
        return color;
    }
}
