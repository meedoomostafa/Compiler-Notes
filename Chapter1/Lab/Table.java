package Chapter1.Lab;

public class Table {
    String Id;
    int Value;
    Table Tail;
    Table Head;
    public Table() {}
    public Table(String id,int value,Table tail){
        Id = id; Value = value; Tail = tail;
    }
}