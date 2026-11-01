package textanalysis;

import textanalysis.dp.EditDistance;

public class EditDistanceTest {

    public static void run(Check c) {
        c.equal("ed identical", 0, EditDistance.distance("kitten", "kitten"));
        c.equal("ed empty first", 5, EditDistance.distance("", "hello"));
        c.equal("ed empty second", 5, EditDistance.distance("hello", ""));
        c.equal("ed both empty", 0, EditDistance.distance("", ""));
        c.equal("ed substitution", 1, EditDistance.distance("cat", "cut"));
        c.equal("ed insertion", 1, EditDistance.distance("cat", "cart"));
        c.equal("ed deletion", 1, EditDistance.distance("cart", "cat"));
        c.equal("ed kitten sitting", 3, EditDistance.distance("kitten", "sitting"));
        c.equal("ed sunday saturday", 3, EditDistance.distance("sunday", "saturday"));
    }
}
