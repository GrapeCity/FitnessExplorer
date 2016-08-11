package grapecity.fitnessexplorer.entities;

/**
 * Created by David.Bickford on 6/20/2016.
 */
public class ActivityTypes
{
    public static final String[] activityTypes = {"In vehicle", "Biking", "On foot", "Idle", "Unknown",
            "Tilting", "", "Walking", "Running", "Aerobics", "Badminton", " Baseball", "Basketball",
            "Biathalon", "Handbiking", "Mountain biking", "Road biking", "Spinning", "Stationary biking",
            "Utility biking", "Boxing", "Calisthenics", "Circuit training", "Cricket", "Dancing",
            "Elliptical", "Fencing", "Football (American)", "Football (Australian)", "Football(Soccer)",
            "Frisbee", "Gardening", "Golf", "Gymnastics", "Handball", "Hiking", "Hockey", "Horseback riding",
            "Housework", "Jumping rope", "Kayaking", "Kettlebell training", "Kickboxing", "Kitesurfing",
            "Martial arts", "Meditation", "Mixed martial arts", "P90X exercises", "Paragliding", "Pilates", "Polo",
            "Racquetball", "Rock climbing", "Rowing", "Rowing machine", "Rugby", "Jogging", "Running on sand",
            "Running (treadmill)", "Sailing", "Scuba diving", "Skateboarding", "Skating", "Cross skating",
            "Inline skating (rollerblading)", "Skiing", "Back-country skiing", "Cross-country skiing",
            "Downhill skiing", "Kite skiing", "Roller skiing", "Sledding", "Sleeping", "Snowboaring",
            "Snowmobile", "Snowshoeing", "Squash", "Stair climbing", "Stair-climbing machine", "Stand-up paddleboarding",
            "Strength training", "Surfing", "Swimming", "Swimming (swimming pool)", "Swimming (open water)",
            "Table tennis (ping pong)", "Team sports", "Tennis", "Treadmill (walking or running)", "Volleyball",
            "Volleyball (beach)", "Volleyball (indoor)", "Wakeboarding", "Walking (fitness)", "Nording walking",
            "Walking (treadmill)", "Waterpolo", "Weightlifting", "Wheelchair", "Windsurfing", "Yoga", "Zumba",
            "Diving", "Ergometer", "Ice skating", "Indoor skating", "Curling", "", "Other (unclassified)",
            "Light sleep", "Deep sleep", "REM sleep", "Awake (during sleep cycle)"};

    public static String findActivity(int i)
    {
        return activityTypes[i];
    }
}
