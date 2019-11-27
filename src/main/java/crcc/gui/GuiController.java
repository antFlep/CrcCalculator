package crcc.gui;

import crcc.checksum.ChecksumCalculator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

public class GuiController {

    @FXML private TextField txfPath;
    @FXML private TableView<ChkFile> tblCrc;

    @FXML private void openExplorer(ActionEvent e) {
        System.out.println("Hello");
        txfPath.setText("This is a non Valid Path");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        List<File> files = fileChooser.showOpenMultipleDialog(txfPath.getScene().getWindow());
        txfPath.setText(files.toString());

        ObservableList<ChkFile> data = tblCrc.getItems();

        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);

            data.add(new ChkFile(file));
        }
    }

    @FXML private void checkChecksum(ActionEvent e) {
        txfPath.setText("Pressed some random button");

        ObservableList<ChkFile> data = tblCrc.getItems();
        IntStream.range(0, data.size()).forEach(i -> {
            data.get(i).setCrc32();
            System.out.println("Checksum is: " + data.get(i).getCrc32());
            tblCrc.refresh();
        });
    }

    @FXML private void handleAddCrc(ActionEvent e) {
        System.out.print("Adding CRC ...");
        ChecksumCalculator.crc32Checksum(null);
    }

    
}
