public class StatFormulas {
    public static double mean(double values[])
    {
        double sumOfVal = 0.0; //<-gets the sum of all the elements in the values[] array
        double sizeOfArray = values.length; //<-gets the size of the values[] array

        for(int i = 0; i < sizeOfArray; i++)
        //loops through entire values[] array
        {
            sumOfVal += values[i]; //<- adds up all the elements that are in values[] array
        }

        return sumOfVal / sizeOfArray;
    }
    public static double median(double values[])
    {
        double medianVal = 0.0; //<- will contain the median of the values[]
        int sizeOfArray = values.length; //<-gets the size of the values[] array

        if (sizeOfArray % 2 == 0) //if the length of the array is even
        {
            medianVal = (values[sizeOfArray / 2 - 1] + values[sizeOfArray / 2]) / 2;
        }
        else //the length of the array is odd
        {
            medianVal = values[(sizeOfArray + 1) / 2 - 1];
        }

        return medianVal;
    }
    public static double std(double values[])
    {
        double meanVal = mean(values); //<- for finding the mean of the values[] array
        double numerator = 0.0; //<-for the numerator of the formula
        double totalVal = 0.0; //<-result of all of the calculation
        double sizeOfArray = values.length; //<-size of the values[] array

        for(int i = 0; i < sizeOfArray; i++)
        //for calculating the numerator by going through the entire values[] array
        {
            numerator += (values[i] - meanVal) * (values[i] - meanVal);
        }

        totalVal = Math.sqrt(numerator/sizeOfArray);

        return totalVal;
    }

}
