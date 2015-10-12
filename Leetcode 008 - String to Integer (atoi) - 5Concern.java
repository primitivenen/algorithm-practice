//Leetcode 008 - String to Integer (atoi).java

//注意点：
1. trim（）
2. null
3. sign（+-）
4. charAt(i) - ‘0’ 范围
5. MAX_VALUE
    - 指明sign
    - -或+ digit
    - result 此时还没有符号
    
public class Solution {
    public static void main (String[] args){
        int result = myAtoi("345");
        System.out.println(result + "");
      
    }
    
    public static int myAtoi(String str) {
        str = str.trim();
        if(str == null || str.length() == 0) return 0;
      
        int i = 0;
        int sign = 1;
        if(str.charAt(0) == '+' || str.charAt(0) == '-'){
            if(str.charAt(i) == '-') sign = -1;
            i++;
        }
      
        int result = 0;
        int digit = 0;
        while(i<str.length()){
            digit = str.charAt(i) - '0';
            if(digit > 9 || digit < 0) {
                break;
            }
            //注意点：
            //sign 指明
            //要 + - digit
            //result 此时还没有正负，所以应时 result > -(...)
            if(sign == 1 && result > (Integer.MAX_VALUE-digit) / 10){
                result = Integer.MAX_VALUE;
                break;
            }
            if(sign == -1 && result > -((Integer.MIN_VALUE+digit) / 10)){
                result = Integer.MIN_VALUE;
                break;
            }
            result = result * 10 + digit;
            i++;
        };
        return result * sign;
    }
}

为什么要 -digit 和 + digit:
以 overflow 的 MAX.VALUE+1 (2147483648)为例：

result = 214748364
(Integer. MAX_VALUE-digit )/10) = (2147483647 - 8) / 10 = 214748363   //直接return MAX_VALUE
(Integer. MAX_VALUE )/10) = 2147483647 / 10 = 214748364 //不return MAX_VALUE, 会在下一步引发overflow