
class matrix{

    public static void main(String args[]) {

        int magicnumber=32;
        for(int i=2;i<=magicnumber;i=i+2)
        {
            if(i%8==0)
            {
                System.out.println(i+"\n");
            }
            else
            {
                System.out.print(i+" ");
            }
        }
    }
}