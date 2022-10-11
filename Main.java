import java.util.*;
import java.io.*;

public class Main {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args){
        utama();
    }

    static void utama(){
        System.out.println("\n=== SELAMAT DATANG DI PROGRAM SISTEM PERSAMAAN LINIER DENGAN OPERASI BARIS ELEMENTER ===");
        System.out.println("1. SPL dengan Eliminasi Gauss");
        System.out.println("2. SPL dengan Eliminasi Gauss-Jordan");
        System.out.println("3. SPL dengan Metode Matriks Balikan");
        System.out.println("4. SPL Kaidah Cramer");
        System.out.println("5. Skalar");
        System.out.println("6. Penjumlahan");
        System.out.println("7. Pengurangan");
        System.out.println("8. Perkalian");
        System.out.println("9. Determinan");
        System.out.println("10. Invers");
        System.out.println("11. Transpose");
        System.out.println("12. Keluar\n");
        System.out.print("Masukkan pilihan: ");
        int pilihan = in.nextInt();
        while(true){
            if (pilihan == 1) gauss();
            else if (pilihan == 2) gaussjordan();
            else if (pilihan == 3) balikan();
            else if (pilihan == 4) cramer();
            else if(pilihan == 5) skalar();
            else if(pilihan == 6) penjumlahan();
            else if(pilihan == 7) pengurangan();
            else if(pilihan == 8) perkalian();
            else if(pilihan == 9) determinan();
            else if(pilihan == 10) invers();
            else if(pilihan == 11) transpose();
            else if(pilihan == 12) System.exit(0);
            else{
                System.out.println("Masukan salah. Silakan masukkan ulang!");
                pilihan = in.nextInt();
            }
        }
    }

    static void masukanelim(Matriks M){
        System.out.println("1. Masukan dari keyboard");
        System.out.println("2. Masukan dari file");
        System.out.print("Masukkan pilihan: ");
        int masukan = in.nextInt();
        System.out.println();
       
        
        while(true){
            if(masukan == 1){
                //Meminta input jumlah persamaan dan jumlah peubah
                System.out.print("Masukkan jumlah persamaan: ");
                int n = in.nextInt();
                System.out.print("Masukkan jumlah peubah: ");
                int m = in.nextInt();
                M.SetBrs(n);
                M.SetKol(m+1);
                M.keyboardSPL(n,m+1);

                break;

            } else if (masukan == 2) {
		                //Membaca matriks yang ada di file
                String namaFile = in.nextLine();
                File file  = new File(namaFile);

                while(!file.exists()){
                    System.out.print("Masukkan nama file: ");
                    namaFile = in.nextLine();
                    file = new File(namaFile);
                }

                M.bacaFile(file);
                System.out.println();
                break;

            }else{
                System.out.print("Masukan salah. Silakan masukkan ulang! ");
                masukan = in.nextInt();   
            }
        }
    }

    static void masukanlain(Matriks M, double[] konstanta){
        System.out.println("1. Masukan dari keyboard");
        System.out.println("2. Masukan dari file");
        System.out.print("Masukkan pilihan: ");
        int masukan = in.nextInt();
        System.out.println();
       
        
        while(true){
            if(masukan == 1){
                //Meminta input jumlah persamaan dan jumlah peubah
                System.out.print("Masukkan jumlah persamaan: ");
                int n = in.nextInt();
                System.out.print("Masukkan jumlah peubah: ");
                int m = in.nextInt();
                M.SetBrs(n);
                M.SetKol(m);
                M.bacaKoefKeyboard(konstanta, n, m+1);
                break;

            } else if (masukan == 2) {
                //Membaca matriks yang ada di file
                String namaFile = in.nextLine();
                File file  = new File(namaFile);

                while(!file.exists()){
                    System.out.print("Masukkan nama file: ");
                    namaFile = in.nextLine();
                    file = new File(namaFile);
                }

                M.bacaKoefFile(file, konstanta);
                System.out.println();
                break;

            }else{
                System.out.print("Masukan salah. Silakan masukkan ulang! ");
                masukan = in.nextInt();   
            }
        }
    }

    static void tulisMatriksAugmented(Matriks M){
        System.out.println("Matriks Augmented: ");
        M.tulisMatriks();
        System.out.println();
    }

    static void gauss(){
        System.out.println("\n=== Eliminasi Gauss ===");
        Matriks M = new Matriks();
        masukanelim(M);
        tulisMatriksAugmented(M);
        Matriks Mmanip = new Matriks(M.GetBrs(),M.GetKol());
        M.salinMatriks(Mmanip);

        M.eliminasiGauss();
        System.out.println("Matriks Eselon Baris: ");
        M.tulisMatriks();  
        double[][] solusi = new double[100][100];
        int[] jumlahSolusi = new int[1];

        Mmanip.eliminasiGaussJordan();
        System.out.println();                          
        //Menuliskan solusi
        Mmanip.solusiEliminasiGaussJordan(solusi, jumlahSolusi);
        Mmanip.tulisSolusi(solusi);
        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();

    }

    static void gaussjordan(){
        System.out.println("\n=== Eliminasi Gauss-Jordan ===");
        Matriks M = new Matriks();
        masukanelim(M);
        tulisMatriksAugmented(M);

        M.eliminasiGaussJordan();
        System.out.println("Matriks Eselon Baris Tereduksi: ");
        M.tulisMatriks();  
        double[][] solusi = new double[100][100];
        int[] jumlahSolusi = new int[1];

        System.out.println();                          
        //Menuliskan solusi
        M.solusiEliminasiGaussJordan(solusi, jumlahSolusi);
        M.tulisSolusi(solusi);
        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void balikan(){
        System.out.println("\n=== Metode Matriks Balikan ===");
        Matriks M = new Matriks();
        Matriks Mtemp = new Matriks();
        Matriks Mhasil = new Matriks();
        double[] konstanta = new double[100];
        masukanlain(M, konstanta);

        if(M.GetBrs() == M.GetKol()){
        //Menerapkan eliminasi Gauss-Jordan untuk memindahkan matriks identitas ke kiri
            Mtemp.SetBrs(M.GetBrs());
            Mtemp.SetKol(2*M.GetKol());
            M.salinMatriks(Mtemp);
            M.isiIdentitas(Mtemp);
            Mtemp.eliminasiGaussJordan();

            if (Mtemp.cekDiagonalInvers()){
                System.out.println("\nMatriks hasil operasi baris elementer: ");
                Mtemp.tulisMatriks();
                System.out.println();
            
                //Menuliskan hasil sistem persamaan
                Mhasil.SetBrs(Mtemp.GetBrs());
                Mhasil.SetKol(1);
                Mtemp.kaliMatriksKonstanta(konstanta, Mhasil);
                Mhasil.tulisHasilSPLIvnvers();
            }else {
                System.out.println("\nMetode Matriks Balikan tidak dapat menyelesaikan persamaan.");
            }
        }else {
            System.out.println("\nMetode Matriks Balikan tidak dapat menyelesaikan persamaan.");
        }

        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void cramer(){
        System.out.println("\n=== Kaidah Cramer ===");
        Matriks M = new Matriks();
        Matriks Mtemp = new Matriks();
        double[] konstanta = new double[100];
        masukanlain(M, konstanta);

        Mtemp.SetBrs(M.GetBrs());
        Mtemp.SetKol(M.GetKol());
        double[] detMi = new double[M.GetKol()+1];

        //Menghitung deteminan M
        M.salinMatriks(Mtemp);
        M.tulisMatriks();
        double detM = M.determinanM();
        System.out.println();

        if (M.GetBrs() == M.GetKol()){
            if ((detM*10)%10 == 0)  System.out.printf("det(M) = %.0f\n", detM);
            else System.out.printf("det(M) = %.2f\n", detM);

            //Menghitung Determinan D(i)
            for(int j=1; j<=M.GetKol(); j++){
                Mtemp.salinMatriks(M);
                M.ubahKol(konstanta,j);
                detMi[j] = M.determinanM();
                if((detMi[j]*10)%10 == 0) System.out.printf("det(M%d) = %.0f\n", j, detMi[j]);
                else System.out.printf("det(M%d) = %.2f\n", j, detMi[j]);
            }

            //Menuliskan solusi tunggal jika ada
            if (detM != 0){
                System.out.println("\nSolusi Sistem Persamaan: ");
                for(int j=1; j<=M.GetKol(); j++){
                    if(((detMi[j]/detM)*10)%10 == 0) System.out.printf("x[%d] = %.0f\n", j, (detMi[j]/detM)); 
                    else System.out.printf("x[%d] = %.2f\n", j, (detMi[j]/detM));
                }
            }else {
                System.out.println("\nKaidah Cramer tidak dapat menyelesaikan persamaan");
            } 
        } else {
            System.out.println("Kaidah Cramer tidak dapat menyelesaikan persamaan");
        } 

        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void skalar(){ // modifikasi
        System.out.println("\n=== Mencari Skalar ===");
        Matriks M = new Matriks();
        masukanbiasa(M);
        System.out.print("Masukkan nilai pengali: ");
        int pengali = in.nextInt();
        System.out.println("Matriks asal: ");
        M.tulisMatriks();
        M.hitungSkalar(pengali);
        System.out.println("\nMatriks hasil: ");
        M.tulisMatriks();

        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void penjumlahan(){ // modifikasi
        System.out.println("\n=== Penjumlahan Matriks ===");
        Matriks M1 = new Matriks();
        Matriks M2 = new Matriks();
        System.out.println("Masukan matriks 1: ");
        masukanbiasa(M1);
        M1.tulisMatriks();
        System.out.println();
        System.out.println("Masukan matriks 2: ");
        masukanbiasa(M2);
        M2.tulisMatriks();
        System.out.println();

        Matriks M3 = new Matriks();

        // cek apakah bisa dijumlahkan
        if (M1.GetBrs() != M2.GetBrs() || M1.GetKol() != M2.GetKol()){
            System.out.println("Matriks tidak dapat dijumlahkan");
        } else {
            M3.SetBrs(M1.GetBrs());
            M3.SetKol(M1.GetKol());
            M3.hitungJumlah(M1, M2);
            System.out.println("Hasil penjumlahan matriks: ");
            M3.tulisMatriks();  
        }
                
        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }


    static void pengurangan(){ // modifikasi
        System.out.println("\n=== Pengurangan Matriks ===");
        Matriks M1 = new Matriks();
        Matriks M2 = new Matriks();
        System.out.println("Masukan matriks 1: ");
        masukanbiasa(M1);
        M1.tulisMatriks();
        System.out.println();
        System.out.println("Masukan matriks 2: ");
        masukanbiasa(M2);
        M2.tulisMatriks();
        System.out.println();

        Matriks M3 = new Matriks();

        // cek apakah bisa dikurangi
        if (M1.GetBrs() != M2.GetBrs() || M1.GetKol() != M2.GetKol()){
            System.out.println("Matriks tidak dapat dikurangi");
        } else {
            M3.SetBrs(M1.GetBrs());
            M3.SetKol(M1.GetKol());
            M3.hitungKurang(M1, M2);
            System.out.println("Hasil pengurangan matriks: ");
            M3.tulisMatriks();  
        }
                
        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void perkalian(){ // modifikasi
        System.out.println("\n=== Perkalian Matriks ===");
        Matriks M1 = new Matriks();
        Matriks M2 = new Matriks();
        System.out.println("Masukan matriks 1: ");
        masukanbiasa(M1);
        M1.tulisMatriks();
        System.out.println();
        System.out.println("Masukan matriks 2: ");
        masukanbiasa(M2);
        M2.tulisMatriks();
        System.out.println();

        Matriks M3 = new Matriks();

        // cek apakah bisa dikalikan
        if (M1.GetKol() != M2.GetBrs()){
            System.out.println("Matriks tidak dapat dikalikan");
        } else {
            M3.SetBrs(M1.GetBrs());
            M3.SetKol(M2.GetKol());
            M3.hitungKali(M1, M2);
            System.out.println("Hasil perkalian matriks: ");
            M3.tulisMatriks();  
        }
                
        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void determinan(){ // modifikasi
        System.out.println("\n=== Mencari Determinan ===");
        Matriks M = new Matriks();
        masukanbiasa(M);
        System.out.println("Matriks: ");
        M.tulisMatriks();
        System.out.println("\nDeterminan matriks : " + M.determinanM());

        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void invers(){ // modifikasi
        System.out.println("\n=== Mencari Invers ===");
        Matriks M = new Matriks();
        Matriks Mtemp = new Matriks();
        masukanbiasa(M);
        M.tulisMatriks();
        Mtemp.SetBrs(M.GetBrs());
        Mtemp.SetKol(2*M.GetKol());
        System.out.println("\nPenambahan Identitas: ");
        M.salinMatriks(Mtemp);
        M.isiIdentitas(Mtemp);
        Mtemp.tulisMatriks();

        System.out.println("Determinan matriks: "  + M.determinanM()); 
        if(M.GetBrs() == M.GetKol() && M.determinanM() != 0){

            //Menerapkan eliminasi Gauss-Jordan untuk memindahkan matriks identitas ke kiri
            Mtemp.eliminasiGaussJordan();

            //hasil eleminasi GaussJordan
            System.out.println("\nHasil eliminasi gauss jordan: ");
            Mtemp.tulisMatriks();
            System.out.println();
            System.out.println("Hasil inverse: ");
            Mtemp.tulisHasilInvers();
        }else {
            System.out.println("\nTidak bisa mencari inverse");
        }
    
            System.out.println();
            System.out.println("Menuju menu utama......");
            utama();
    }


    static void transpose(){ // modifikasi
        System.out.println("\n=== Mencari Transpose ===");
        Matriks M = new Matriks();
        masukanbiasa(M);
        M.tulisMatriks();

        Matriks Mtemp = new Matriks();
        Mtemp.SetBrs(M.GetKol());
        Mtemp.SetKol(M.GetBrs());
        
        Mtemp.cariTranspose(M);
        System.out.println("Transpose matriks : ");
        Mtemp.tulisMatriks();

        System.out.println();
        System.out.println("Menuju menu utama......");
        utama();
    }

    static void masukanbiasa(Matriks M){ // modifikasi
        System.out.println("1. Masukan dari keyboard");
        System.out.println("2. Masukan dari file");
        System.out.print("Masukkan pilihan: ");
        int masukan = in.nextInt();
       
        
        while(true){
            if(masukan == 1){
                //Meminta input ordo matriks
                System.out.print("Masukkan ordo matriks [baris] [kolom]: ");
                int m = in.nextInt();
                int n = in.nextInt();
                M.SetBrs(m);
                M.SetKol(n);
                M.keyboardBiasa(m,n);

                break;

            } else if (masukan == 2) {
                        //Membaca matriks yang ada di file
                String namaFile = in.nextLine();
                File file  = new File(namaFile);

                while(!file.exists()){
                    System.out.print("Masukkan nama file: ");
                    namaFile = in.nextLine();
                    file = new File(namaFile);
                }
                M.bacaFile(file);
                break;

            }else{
                System.out.print("Masukan salah. Silakan masukkan ulang! ");
                masukan = in.nextInt();   
            }
        }
    }


}

