//longest sequence of max product
import java.io.*;
import java.util.Arrays;
public class LongestProductSequence {
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "0 "+br.readLine()+" 0";
		String inp[] = in.split(" ");
		int num[] = new int[inp.length];
		for(int i=0;i<inp.length;i++){
			num[i]=Integer.parseInt(inp[i]);
		}
		//--now to deal with situations like 0 -2 0 -1 0
		boolean isolatedNegative=true;;
		for(int i=1;i<num.length-1;i++){
			if( num[i]<0 && !(num[i-1]==0 && num[i+1]==0) ){isolatedNegative = false;break;}
		}
		if(isolatedNegative){
			int num1[] = new int[num.length]; 
			System.arraycopy(num,0,num1,0,num.length);
			Arrays.sort(num1);
			for(int i=num1.length-1;i>=0;i--){
				if(num1[i]<0){System.out.println(num1[i]);break;}
			}
		}
		else{//else starts
		int start = 0;
		int end = 0;
		for(int i=0;i<num.length;i++){
			int countn =0;
			int nz =nextZero(i,num);
			if(nz==-1)break;
			for(int j=i;j<nz;j++){
				if(num[j]<0){countn++;}
			}
			
			if(countn%2 != 0 && countn>0){
				int fni=0;//first negative index
				int lni=0;//last negative index
				for(int p=i;p<nz;p++){
					if(num[p]<0){fni=p;break;}
				}
				for(int p=nz-1;p>=i;p--){
					if(num[p]<0){lni=p;break;}
				}
				if(product(fni+1,nz-1,num) <= product(i+1,lni-1,num) ){
					num[lni]=0;
				}
				else
					num[fni]=0;
			}
			i=nz-1;
		}
		//System.out.println("d"+Arrays.toString(num));
		int f=0;
		int pro=0;
		while(nextZero(f,num)!=-1){
			int nz = nextZero(f,num);
			int temppro=product(f+1,nz-1,num);
			if( temppro > pro  ){
				pro = temppro;
				start = f+1;
				end = nz-1;
			}
			f=nz;
		}
		String ans="";
		for(int i=start;i<=end;i++){
			ans+=num[i]+" ";
		}
		System.out.println(ans.trim());
		}//else ends
		
	}
	public static int product(int s,int e,int[] num){
		if(e-s>=0){
		int p=1;
		for(int i=s;i<=e;i++){
			p*=num[i];
		}
		return p;}
		else return 0;
	}
	public static int nextZero(int i,int[] in){
		for(int j=i+1;j<in.length;j++){
			if(in[j]==0)return j;
		}
		return -1;
	}
}
