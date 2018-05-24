package sample.Function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Readfile {


    public List<Line> lines;

    String[] input;



    Pattern p = Pattern.compile("^([0-9]*) ([0-9]{2}):([0-9]{2}):([0-9]{2}),[0-9]{3} --> ([0-9]{2}):([0-9]{2}):([0-9]{2}),[0-9]{3} (.*)");
    public List<Line> start(String file){
        try {
            input=readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines = new ArrayList<Line>();
        for (int i = 0; i < input.length; i++) {
            parseLine(i);
        }

        for (Line line : lines) {
            System.out.println(line);
        }
        return lines;
    }
    public String[] readLines(String filename) throws IOException
    {
        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null)
        {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }
    private void parseLine(int i) {
        int count = 0;
        String s = input[i];
        Matcher m = p.matcher(s);
        if (m.matches()) {
            // this is new Line
            int index = Integer.parseInt(m.group(1));
            int h1 = Integer.parseInt(m.group(2));
            int m1 = Integer.parseInt(m.group(3));
            int s1 = Integer.parseInt(m.group(4));
            int seconds1 = h1 * 3600 + m1 * 60 + s1;
            int h2 = Integer.parseInt(m.group(5));
            int m2 = Integer.parseInt(m.group(6));
            int s2 = Integer.parseInt(m.group(7));
            int seconds2 = h2 * 3600 + m2 * 60 + s2;
            String str = m.group(8); // actual text

            // add line to list
            Line lo = new Line(index, str, seconds1, seconds2);
            lines.add(lo);
        } else {
            if(lines.size()>0) {
                // no match - this is a continuation - add text to end of last line
                lines.get(lines.size() - 1).append(s);
            }
        }
    }
   public class Line {
        int index;
        String text;
        int start, end;

       public int getIndex() {
           return index;
       }

       public String getText() {
           return text;
       }

       public int getStart() {
           return start;
       }

       public int getEnd() {
           return end;
       }


       public Line(int i, String str, int s1, int s2) {
            this.index = i;
            this.text = str;
            this.start = s1;
            this.end = s2;
        }

        public void append(String str) {
            text = text + " " + str;
        }

        @Override
        public String toString() {
            return "Line[" + index + "][" + start + "][" + end + "][" + text + "]";
        }
    }

}