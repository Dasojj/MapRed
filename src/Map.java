import java.awt.*;
import java.io.*;
import java.util.Random;

public class Map {

    private static int [][] matrix;

    public static final int WALL   = 1;
    public static final int BRICK  = 2;
    public static final int WATER  = 3;
    public static final int EMPTY  = 4;
    public static final int GROUND = 0;
    public static final int SMALLB = 5;
    public static final int MEDIUMB = 6;
    public static final int BIGB = 7;
    public static final int SPEEDB = 8;
    public static final int BLOCK_SIZE = 32;

    private static Random r = new Random();


    static {
        createWorld(100,100);

    }


    public static int set (int cell, int layer, int code){
        int tmp = clear(cell, layer);
        return tmp|(code<<layer*8);
    }
    public static int get (int cell, int layer){
        int mask = 255 << layer * 8;
        return (cell & mask)>>layer*8;
    }
    public static int clear (int cell, int layer) {
        int mask = 255 << layer * 8;
        mask = ~mask;
        return cell & mask;
    }


    public static int getBlock (int r, int c) {
        return get(matrix[r][c], 0);
    }

    public static void spawnBlock (double xW, double yW , int block) {
        int row = getRowByY(yW);
        int col = getColByX(xW);
        matrix[row][col] = set(matrix[row][col], 0, block);
    }

    //public static int getBuster (int r, int c) {
    //    return get(matrix[r][c], 1);
    //}

    public static void spawnBuster (double xW, double yW , int buster) {
        int row = getRowByY(yW);
        int col = getColByX(xW);
        matrix[row][col] = set(matrix[row][col], 1, buster);
    }

    public static void createWorld (int rows, int cols) {
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i][0] = set(matrix[i][0], 0, WALL);
            matrix[i][cols-1] = set(matrix[i][cols-1], 0, WALL);
            matrix[0][i] = set(matrix[0][i], 0, WALL);
            matrix[rows-1][i] = set(matrix[rows-1][i], 0, WALL);
        }
        int tmpcheck;
        for (int i = 3; i < rows-1; i++) {
            tmpcheck = 4;
            if(get(matrix[i-1][i-2], 0)==WALL)tmpcheck--;
            if(get(matrix[i+1][i-2], 0)==WALL)tmpcheck--;
            if(get(matrix[i][i-1], 0)==WALL)tmpcheck--;
            if(get(matrix[i][i-3], 0)==WALL)tmpcheck--;
            if(r.nextInt(100)<=14*tmpcheck){
                matrix[i][i-2] = set(matrix[i][i-2], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i+1][i-2] = set(matrix[i+1][i-2], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i][i-1] = set(matrix[i][i-1], 0, WALL);
            }
        }
        for (int i = cols-2; i > 1; i--) {
            tmpcheck = 4;
            if(get(matrix[i-1][i], 0)==WALL)tmpcheck--;
            if(get(matrix[i+1][i], 0)==WALL)tmpcheck--;
            if(get(matrix[i][i-1], 0)==WALL)tmpcheck--;
            if(get(matrix[i][i+1], 0)==WALL)tmpcheck--;
            if(r.nextInt(100)<=14*tmpcheck){
                matrix[i][i] = set(matrix[i][i], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i+1][i] = set(matrix[i+1][i], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i][i] = set(matrix[i][i], 0, WALL);
            }
        }
        for (int i = 1; i < rows-1; i++){
            tmpcheck = 4;
            if(get(matrix[i-1][i], 0)==WALL)tmpcheck--;
            if(get(matrix[i+1][i], 0)==WALL)tmpcheck--;
            if(get(matrix[i][i-1], 0)==WALL)tmpcheck--;
            if(get(matrix[i][i+1], 0)==WALL)tmpcheck--;
            if(r.nextInt(100)<=14*tmpcheck){
                matrix[i][35] = set(matrix[i][35], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i][36] = set(matrix[i][36], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i][34] = set(matrix[i][34], 0, WALL);
            }
            if(r.nextInt(100)<=10*(tmpcheck-1)){
                matrix[i][37] = set(matrix[i][37], 0, WALL);
            }
            if(r.nextInt(100)<=10*(tmpcheck-1)){
                matrix[i][33] = set(matrix[i][33], 0, WALL);
            }
        }
        for (int i = 1; i < rows-1; i++){
            tmpcheck = 4;
            if(get(matrix[i][65], 0)==WALL)tmpcheck--;
            if(get(matrix[i][64], 0)==WALL)tmpcheck--;
            if(get(matrix[i][61], 0)==WALL)tmpcheck--;
            if(get(matrix[i][62], 0)==WALL)tmpcheck--;
            if(r.nextInt(100)<=14*tmpcheck){
                matrix[i][63] = set(matrix[i][63], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i][64] = set(matrix[i][64], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[i][62] = set(matrix[i][62], 0, WALL);
            }
            if(r.nextInt(100)<=10*(tmpcheck-1)){
                matrix[i][65] = set(matrix[i][65], 0, WALL);
            }
            if(r.nextInt(100)<=10*(tmpcheck-1)){
                matrix[i][61] = set(matrix[i][61], 0, WALL);
            }
        }
        for (int i = 1; i < cols-1; i++){
            tmpcheck = 4;
            if(get(matrix[70][i], 0)==WALL)tmpcheck--;
            if(get(matrix[66][i], 0)==WALL)tmpcheck--;
            if(get(matrix[67][i], 0)==WALL)tmpcheck--;
            if(get(matrix[69][i], 0)==WALL)tmpcheck--;
            if(r.nextInt(100)<=14*tmpcheck){
                matrix[68][i] = set(matrix[68][i], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[69][i] = set(matrix[69][i], 0, WALL);
            }
            if(r.nextInt(100)<=14*(tmpcheck-1)){
                matrix[67][i] = set(matrix[67][i], 0, WALL);
            }
            if(r.nextInt(100)<=10*(tmpcheck-1)){
                matrix[66][i] = set(matrix[66][i], 0, WALL);
            }
            if(r.nextInt(100)<=10*(tmpcheck-1)){
                matrix[70][i] = set(matrix[70][i], 0, WALL);
            }
        }
        for(int i = 1; i < rows-1; i++){
            for(int j = 1; j < cols-1; j++){
                //50% замена стены на кирпич
                //15% кирпич прост так
                //остальное 60/40 вода/суша
                if(get(matrix[i][j], 0)==WALL)
                    if(r.nextInt(100)<10)
                        matrix[i][j]=set(matrix[i][j], 0, BRICK);
                if(get(matrix[i][j], 0) != WALL)
                    if(r.nextInt(100)<=20)
                        matrix[i][j] = set(matrix[i][j], 0, BRICK);
                if(r.nextInt(100)<=5)
                    matrix[i][j] = set(matrix[i][j], 0, WALL);
                if(get(matrix[i][j], 0)!=BRICK && get(matrix[i][j], 0) != WALL){
                    if(r.nextInt(100)<=80)
                        matrix[i][j] = set(matrix[i][j], 0, GROUND);
                    else matrix[i][j] = set(matrix[i][j], 0, WATER);
                }
            }
        }
    }

    public static void saveMatrix() {
        File file = new File(((new File(".").getAbsolutePath())+"MapConfig.txt"));
        try{
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter (file)) {
            int block;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    block = get(matrix[i][j], 0);
                    writer.write(block + " ");
                }
                writer.append('\n');
            }
            writer.flush();
            System.out.println("Successfully saved!");
        }
        catch (IOException ex){
            System.out.println("Map saving failed.");
            System.out.println(ex.getMessage());
        }
    }

    //НА ДОРАБОТКЕ
    public static void loadMatrix(){
        File file = new File(((new File(".").getAbsolutePath())+"MapConfig.txt"));
        int row=0, col=0;
        BufferedReader objReader = null;
        try{
            char c;
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(file));
            while (((strCurrentLine = objReader.readLine()) != null)) {
                for(int i = 0; i < strCurrentLine.length(); i++) {
                    c = strCurrentLine.charAt(i);
                    if (c == ' ') col++;
                    else matrix[row][col] = set(matrix[row][col], 0, (int)c-48);
                }
                col=0;
                row++;
            }
            System.out.println("Map loaded!");
        }
        catch (IOException ex){
            System.out.println("Map loading failed.");
            ex.printStackTrace();
        }
        finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static int getColByX (double X) {
        return (int)X / BLOCK_SIZE;
    }
    public static int getRowByY (double Y) {
        return (int)Y / BLOCK_SIZE;
    }

    public static void paint (Graphics g) {

        int row1, col1, row2, col2;
        row1 = getRowByY(Camera.getTop());
        col1 = getColByX(Camera.getLeft());

        row2 = getRowByY(Camera.getBottom());
        col2 = getColByX(Camera.getRight());


        int screenX, screenY, block, buster;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <=  col2; j++) {
                block = get(matrix[i][j], 0);
                buster = get(matrix[i][j], 1);
                screenX = Camera.getScreenX(j * Map.BLOCK_SIZE);
                screenY = Camera.getScreenY(i * Map.BLOCK_SIZE);
                ImageHelper.paint(g, block, screenX, screenY);
                if(buster!=0) ImageHelper.paint(g, buster, screenX, screenY);
            }
        }

    }



}
