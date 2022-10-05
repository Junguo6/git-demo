import java.util.ArrayList;
import java.util.Scanner;

interface ISignUP{
    public void print();
    public boolean addStudent(int stuType);
    public static IParams parse() throws Exception{
        try {
            return new SchoolSystem.School();//返回已实现的接口类
        }catch (Exception e){e.printStackTrace();}
        return null;
    };
}
interface  IParams{
    public int getBig();
    public int getMedium();
    public int getSmall();
    public String[] parse(String input);

    public ArrayList<Integer> getPIamSignUp();
}
public class SchoolSystem implements ISignUP {
    protected Integer big;
    protected Integer medium;
    protected Integer small;


    public SchoolSystem(Integer big, Integer medium, Integer small) {
        this.big=big;
        this.medium=medium;
        this.small=small;
    }

    @Override
    public void print() {
        System.out.println("大中小班各剩余名额为"+this.big+"\t"+this.medium+"\t"+this.small);
    }

    @Override
    public boolean addStudent(int stuType) {
        if (stuType==1&&this.big!=0){
                this.big--;
                return true;
        }else if (stuType==2&&this.medium!=0){
                this.medium--;
                return true;
        }else if (stuType==3&&this.small!=0){
                this.small--;
                return true;
        }else{
        return false;}
    }


    public static class School implements IParams{
        Scanner SC=new Scanner(System.in);
        public int Big;
        public int Medium;
        public int Small;


        @Override
        public String[] parse(String input){
            String regex = "[\\p{Blank}]+";
            String[] S= input.split(regex);
            this.Big=Integer.parseInt(S[0]);
            this.Medium=Integer.parseInt(S[1]);
            this.Small=Integer.parseInt(S[2]);
            return S;
        }
        @Override
        public int getBig() {
           return this.Big;

        }

        @Override
        public int getMedium() {
            return this.Medium;
        }

        @Override
        public int getSmall() {
            return this.Small;
        }

        @Override
        public ArrayList<Integer> getPIamSignUp() {
            String A=SC.next();
            ArrayList<Integer> C=new ArrayList<>();
            for (int i = 0; i < A.length(); i++) {
                C.add(Integer.parseInt(A.substring(i,i+1)));//将输入的混合班级序号放入到集合中
            }
            return C;
        }
    }

    public static void main(String[] args) throws Exception {
        IParams params =ISignUP.parse();
        Scanner SC2=new Scanner(System.in);
        SC2.useDelimiter("\n");       //识别输入的空格
        System.out.println("请依次输入大中小班各剩名额（用空格隔开）");
        String[] n=params.parse(SC2.next());
        SchoolSystem sc=new SchoolSystem(params.getBig(),params.getMedium(),params.getSmall());
        System.out.println("请输入入学班级");
        ArrayList<Integer> plan=params.getPIamSignUp();
        for (int i = 0; i < plan.size(); i++) {
            boolean a=sc.addStudent(plan.get(i));
            if (plan.get(i)==1&& a){
                System.out.println("大班录入成功");
            }else if (plan.get(i)==2&& a){
                System.out.println("中班录入成功");
            }else if (plan.get(i)==3&& a){
                System.out.println("小班录入成功");
            }else {
                System.out.println("录入失败，名额不足");
            }
        }
        sc.print();
    }
}
