import java.util.ArrayList;
/**
 *
 * Class responsible for parsing the issues string array.
 * Currently aim is to get comments, amount of authors.
 */
public class Issues extends GitObject {
    
    private static final String TITLE = "title";
    private static final String COMMENT = "comment";
    private static final int NUM_FIELDS = 6;
    private ArrayList<Issue> issues;
    
    // == constructors ==
     
    public Issues(String[] data) {
        super(data);
        issues = new ArrayList<>();
        prepIssues();
    }

    // == public methods ==

    public void getData(String keyword) {
        System.out.print(toString());
    }
    
    public String toString() {
        String ret = "";
        for (int i = 0; i < issues.size(); i++) {
            ret += (i + 1) + ") " + issues.get(i).toString() + "\n";
        }
        return ret;
    }

    // == private methods ==

    private void prepIssues() {
        String title = "";
        String date = "";
        String time = "";
        String url = "";
        String comments_url = "";
        String body = "";
        int count = 0;

        for (int i = 0; i < getData().length; i++) {
             
            String[] args = getData()[i].split(":");

            if (args[0].toLowerCase().contains("title")) {

                 title = args[1];
                 count++;
            }
            else if (args[0].toLowerCase().contains("body")) {
                body = args[1];
                count++;
            }
            else if (args[0].toLowerCase().contains("date")) {
                String[] r = args[1].split("T");
                date = r[0];
                time = r[1] + ":" + args[2] + ":" + args[3];
                count++;
            }
            else if (args[0].toLowerCase().equals("{\"url\"")) {
                url = "";
                for (int j = 1; j < args.length; j++) url += args[j];
                count++;
            }
            else if (args[0].toLowerCase().contains("comments_url")) {
                comments_url = "";
                for (int j = 1; j < args.length; j++) comments_url += args[j];
                count++;
            }
            else if (args[0].toLowerCase().contains("body")) {
                body = args[1];
                count++;
            }

            if (count == NUM_FIELDS) {
                count = 0;
                //System.out.println(title + "\n" + date + "\n" + url + "\n" + body);
                //System.out.println(title);
                Issue issue = new Issue(title, date, time, url, comments_url, body);
                //System.out.println(issue);
                issues.add(issue);
            }
        }
    }

    // == inner class ==
    private class Issue {
        public String title;
        public String date;
        public String time;
        public String url;
        public String comments_url;
        public String body;

        public Issue(String title, String date, String time, String url, String comments_url, String body) {
            this.title = title;
            this.date = date;
            this.time = time;
            this.url = url;
            this.comments_url = comments_url;
            this.body = body;
        }

        public String toString() {
            return "Title: " + title 
                + ", Date: " + date
                + ", Time: " + time
                + ", Body: " + body;
        }
    }
}
