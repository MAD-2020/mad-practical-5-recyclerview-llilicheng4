package sg.edu.np.mad.mad_recyclerview;
public class Task{
    //task has:
    // attribute: task name
    private String Name;
    //function: removetask and addtask to be added to viewholders
    public Task(String name){
        this.Name = name;
    };

    public String getName() {
        return Name;
    }

    public void setName(String newName){
        this.Name = newName;
    }

}
