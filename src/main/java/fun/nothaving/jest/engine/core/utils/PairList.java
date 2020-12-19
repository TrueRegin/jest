package fun.nothaving.jest.engine.core.utils;

import java.util.HashMap;
import java.util.UUID;

import fun.nothaving.jest.engine.constants.Errors;

// ! Warning. This class is very complicated, and uses names that can easily confuse you.
// ! The basic concept of a PairList is a list of Strings to Objects in which you can
// ! get the value of the Object given the string and vice versa.

// ! If you wish to read the code, go ahead, this is definitely a class that needs recoding.

/**
 * A list of String pairs to <T> values in which if you know either the label or value of a pair you can get the other corresponding data.
 * This makes it easy to have string names and id names for objects.
 * 
 * The efficiency of this code is definitely not good, it is only intended to provide an easy way to link two values until a better version can be developed by somebody else.
 * 
 */
public class PairList<T> {
    HashMap<String, T> valuesList;
    HashMap<T, String> labelsList;

    /**
     * Stores a mapping of labels and values to each other in two separate hashmaps, the labels and values
     * can't be changed after you enter them, they are static.
     * 
     * @param labels - A list of String values of some static length N
     * @param values - A list of <T> values of some static length N
     */
    public PairList(String[] labels, T[] values) {
        if(labels.length != values.length) {
            new Error(Errors.DATA_ERROR_INTERNAL);
        }
        
        this.labelsList = new HashMap<T, String>();
        this.valuesList = new HashMap<String, T>();

        for(int i = 0; i < labels.length; i++) {
            String currLabel = labels[i];
            T currValue = values[i];
            valuesList.put(currLabel, currValue);
            labelsList.put(currValue, currLabel);
        }
    }

    /**
     * Allows you to get a corresponding <T> value from the PairList if you know its corresponding label. 
     * 
     * @param label - The String label corresponding to some <T> value from the input values.
     * @return the corresponding value of the inputted label, which has to exist.
     */
    public T getByLabel(String label) {
        return valuesList.get(label);
    }

    /**
     * Allows you to get a corresponding String label value from the PairList if you know its corresponding value.
     * 
     * @param value - The <T> value corresponding to some String label in the input labels
     * @return
     */
    public String getByValue(T value) {
        return labelsList.get(value);
    }
}
