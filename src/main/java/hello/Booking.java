package hello;

public class Booking{
   int day; //0 indexing
   int month;
   String time;
   int num_attendees;
   String room_name;


   Booking(){}
   Booking(int d, String t, int n, String r){
       this.day = d;
       this.time = t;
       this.num_attendees = n;
       this.room_name = r;
   }

   //Begin getters and setters
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum_attendees() {
        return num_attendees;
    }

    public void setNum_attendees(int num_attendees) {
        this.num_attendees = num_attendees;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


}