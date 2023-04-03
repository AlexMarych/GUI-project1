import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public
class S28507Set01
        extends Frame {

    public static void main(String[] args) {
       Binfilewrite.Write();
        new S28507Set01();
    }

    public S28507Set01() {
        this.setSize(1920, 1080);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = this.getSize().width / 2 - 1, y = this.getSize().height / 2 - 1, it = 1, add = 1, number = 2, get = 0;
        g.fillRect(x, y, 1, 1);

        while (number < 1080 * 1080) {
            for (int i = 0; i < it; i++) {
                x += add;
                if (get == FPrimes.Prime_num.size()-1)get=0;
                if (FPrimes.Prime_num.get(get) == number) {
                    g.setColor(Color.RED);
                    get++;

                }
                g.fillRect(x, y, 1, 1);
                number++;
                g.setColor(Color.black);

            }
            add *= -1;
            for (int i = 0; i < it; i++) {
                y += add;
                if (FPrimes.Prime_num.get(get) == number) {
                    g.setColor(Color.RED);
                    get++;
                }
                g.fillRect(x, y, 1, 1);
                number++;
                g.setColor(Color.black);
            }
            it++;
        }
    }
}
class Binfilewrite {
    public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
    }

    public static void AmountInLline(ArrayList<Long>  amount_byte,FileOutputStream fos, int power) throws IOException {
        fos.write((byte) (amount_byte.get(power) >> 56));
        fos.write((byte) (amount_byte.get(power) >> 48));
        fos.write((byte) (amount_byte.get(power) >> 40));
        fos.write((byte) (amount_byte.get(power) >> 32));
        fos.write((byte) (amount_byte.get(power) >> 24));
        fos.write((byte) (amount_byte.get(power) >> 16));
        fos.write((byte) (amount_byte.get(power) >> 8));
        fos.write((byte) (amount_byte.get(power) >> 0));
    }

    public static void Write() {
        FPrimes.Add_prime(1080 * 1080, FPrimes.Prime_num);

        int power = 1;
        long help ;
        ArrayList<Long> amount_byte = new ArrayList<>();
        for (int i = 0; i < FPrimes.Prime_num.size(); i++) {
            if (FPrimes.Prime_num.get(i) > (Math.pow(2, 8 * power)) - 1) {
                help = i;
                amount_byte.add(help);
                power++;
            }
        }

        power = 1;
        int iter = 0;

        try {
            FileOutputStream fos = new FileOutputStream("Prime.bin");
            AmountInLline(amount_byte,fos,0);
            for (int i = 0; i < FPrimes.Prime_num.size(); i++) {
                if (amount_byte.size() > power && amount_byte.get(power) == iter) {
                    AmountInLline(amount_byte,fos,power);
                    power++;
                    iter = 0;
                }
                iter++;
                fos.write(FPrimes.Prime_num.get(i));
            }
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
class FPrimes {
    static ArrayList<Integer> Prime_num = new ArrayList<>();

    public static void Add_prime(int maxVal,ArrayList<Integer> primes) {
        boolean[] isPrime = new boolean[maxVal];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < Math.sqrt(maxVal); i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < maxVal; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < maxVal; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }
}