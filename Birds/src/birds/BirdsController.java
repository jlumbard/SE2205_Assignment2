package birds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader aka "BROCK AND KEV"
 */
public class BirdsController implements Initializable {
    @FXML
    private Text birdName;
    @FXML
    private Text birdAbout;
    @FXML
    private Text nameTitle;
    @FXML
    private Text sizeTitle;
    @FXML
    private MenuBar mainMenu;
    @FXML
    private Button firstBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button previousBtn;
    @FXML
    private Button lastBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button playBtn;
    @FXML
    private Button stopBtn;
    @FXML
    private Button findBtn;
    @FXML
    private Pane controlPane;
    @FXML
    private Pane contentPane;
    @FXML
    private Pane mediaPane;
    @FXML
    private Pane searchPane;
    @FXML
    private Pane buttonPane;
    @FXML
    private ImageView birdImage;
    @FXML
    private TextField nameEntry;
    @FXML
    private ComboBox sizeBox;

    
    
    //not fxml tagged, reg variables
    private MediaPlayer media;
    private OrderedDictionary tree;
    private BirdRecord recordToUse;
    private BirdRecord temp;
   
    public void fillDictionary(){
        loadDictionary("BirdsDatabase.txt");
        try{
        recordToUse = tree.smallest();//IMPORTANT
        refreshPaneData(recordToUse);
        }catch(DictionaryException e){
            displayError(e.toString());
        }
        show(true);
    }
    public void loadDictionary(String fileName){
        
        String birdSizeFromDic;
        String birdNameFromDic;
        String birdDescriptionFromDic;
        tree = new OrderedDictionary();
        Scanner myScanner;
        try{
        myScanner = new Scanner(new File(fileName));
        while(myScanner.hasNext()){
            birdSizeFromDic = myScanner.nextLine();
            birdNameFromDic = myScanner.nextLine();
            birdDescriptionFromDic = myScanner.nextLine();
            DataKey key = new DataKey(birdNameFromDic,Integer.parseInt(birdSizeFromDic));
            BirdRecord record = new BirdRecord(key,birdDescriptionFromDic,"src/sounds/"+birdNameFromDic+".mp3","images/"+birdNameFromDic+".jpg");
            try{
                tree.insert(record);
            }catch(DictionaryException e){
                displayError(e.toString());
            }
        }
        }catch(FileNotFoundException | NumberFormatException e){
            displayError(e.toString());
        }
    }
    public void refreshPaneData(BirdRecord R){
        //ws
        birdName.setText(R.getDataKey().getBirdName());
        birdAbout.setText(R.getAbout());
        try{
            Image bird = new Image(R.getImage());
            birdImage.setImage(bird);
        }catch(Exception e){
            displayError(e.toString());
        }
    }
    public void changeStyle(){
        searchPane.setStyle("-fx-background-color: white;");
        sizeTitle.setStyle("-fx-background-color: white;");
        nameTitle.setStyle("-fx-background-color: white;");
        firstBtn.setStyle("-fx-background-color: red;");
        nextBtn.setStyle("-fx-background-color: red;");
        previousBtn.setStyle("-fx-background-color: red;");
        lastBtn.setStyle("-fx-background-color: red;");
        deleteBtn.setStyle("-fx-background-color: #f45942;");
        playBtn.setStyle("-fx-background-color: green;");
        stopBtn.setStyle("-fx-background-color: green;");
        findBtn.setStyle("-fx-background-color: pink;");
        
    }
    public void show(boolean value){
        //ws 
        controlPane.setVisible(value);
        contentPane.setVisible(value);
        mediaPane.setVisible(value);
        searchPane.setVisible(value); 
        buttonPane.setVisible(value);
    }
    public void first(){
        try{
            recordToUse = tree.smallest();//<3 tree use
            refreshPaneData(recordToUse);
        }catch(DictionaryException e){
            displayError(e.toString());   
        }
    }
    public void next(){
        try{
            recordToUse = tree.successor(recordToUse.getDataKey());
            refreshPaneData(recordToUse);
        }catch(DictionaryException e){
            displayError(e.toString());
        }   
    }
    public void last(){
        try{
            recordToUse = tree.largest();
            refreshPaneData(recordToUse);
        }catch(DictionaryException e){
            displayError(e.toString());
        }
    }
    public void previous(){
        try{
            recordToUse = tree.predecessor(recordToUse.getDataKey());
            refreshPaneData(recordToUse);
        }catch(DictionaryException e){
            displayError(e.toString());
        }
    }
    
    public void delete() throws DictionaryException{
        temp = null;
        try{
            BirdRecord next = tree.successor(recordToUse.getDataKey());
            temp = next;
        }catch(DictionaryException e){
            try{
                BirdRecord prev = tree.predecessor(recordToUse.getDataKey());
                temp = prev;
            }catch(DictionaryException ex){
                displayError(ex.toString());
            }
        }
        try{
            tree.remove(recordToUse.getDataKey());
        }catch(DictionaryException e){
            displayError(e.toString());
        }
        if(!tree.isEmpty()){
           if(temp !=null){
               recordToUse = temp;
           }
           refreshPaneData(recordToUse);
        }else{
            show(false);
            displayError("OUT OF BIRDS TO DISPLAY");
        }   
    }
    public void play(){
        String soundFile = recordToUse.getSound();
        Media banger = new Media(new File(soundFile).toURI().toString());
        media  = new MediaPlayer(banger);
        media.play();
    }
    
    public void stop(){
        if(media!=null){
            media.stop();}
        else{
        displayError("nothing to stop");
        }
    }
    public void find(){
        String searchEntry = nameEntry.getText();
        String size = sizeBox.getValue().toString().toLowerCase();
        int sizeBird;
        if("small".equals(size)){
                sizeBird = 1;
            }
        if("medium".equals(size)){
                sizeBird = 2;
            }
        else{
                sizeBird = 3;
            }
        try{
            recordToUse = tree.find(new DataKey(searchEntry,sizeBird));
            refreshPaneData(recordToUse);
        }catch(DictionaryException e){
            displayError(e.toString());
        }
    }
    @FXML
    public void exit() {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }
    private void displayError(String Message) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent AlertError = loader.load();
            AlertController controller = (AlertController) loader.getController();
            Scene scene = new Scene(AlertError);
            Stage stage = new Stage();
            stage.setScene(scene);
            String errorMessage = Message.substring(Message.indexOf(":")+1);
            stage.getIcons().add(new Image("file:src/birds/WesternLogo.png"));
            controller.setMessage(errorMessage);
            stage.initModality(Modality.APPLICATION_MODAL);//I have never known what this line does but I always include it 
            stage.showAndWait();

        } catch (IOException ex1) {
            displayError(ex1.toString());
            //lmao this defs could be an inf loop but its unlikely, still bad practice 
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sizeBox.getItems().addAll("Small","Medium","Large");
        sizeBox.setValue(sizeBox.getItems().get(0));
        changeStyle();
        show(false); 
    }
    
    

}