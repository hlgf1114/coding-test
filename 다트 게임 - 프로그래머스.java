public class Solution {

    public static void main(String[] args) {

        System.out.println(solution("1D2S#10S"));
    }

    public static int solution(String dartResult) {

        char[] command = dartResult.toCharArray();

        int result = 0;
        int numSave = 0;
        String num = "";
        boolean before = false;
        for (char c : command) {

            System.out.println("---" + c);


            if(c >= '0' && c <= '9') {
                if(before && !num.equals("")) {
                    result += numSave;
                    numSave = Integer.parseInt(num);
                    num = "";
                    before = false;
                }
                else if(before && num.equals("")) {
                    before = false;
                }
                num += c;
            }

            else if(c == 'S') {
                num = String.valueOf(Integer.parseInt(num));
                before = true;
            }
            else if(c == 'D') {
                num = String.valueOf((int) Math.pow(Integer.parseInt(num), 2));
                before = true;
            }
            else if(c == 'T') {
                num = String.valueOf((int) Math.pow(Integer.parseInt(num), 3));
                before = true;
            }
            else if(c == '*') {
                numSave *= 2;
                result += numSave;
                numSave = Integer.parseInt(num) * 2;
                num = "";
                before = true;
            }
            else if(c == '#') {
                if(numSave != 0) result += numSave;
                numSave = Integer.parseInt(num) * -1;
                num = "";
                before = true;
            }

            System.out.println(numSave + " " + num + " total" + result);

        }

        if(numSave != 0) result += numSave;
        if(!num.equals("")) result += Integer.parseInt(num);

        return result;
    }
}
