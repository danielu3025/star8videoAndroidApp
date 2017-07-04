package com.apps.koru.star8_video_app.downloadclass;

import com.apps.koru.star8_video_app.events.DeleteVideosEvent;
import com.apps.koru.star8_video_app.objects.Model;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;

public class DeleteFilesHandler {
    private Model appModel = Model.getInstance();

    public DeleteFilesHandler() {
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void onEvent(DeleteVideosEvent event) {
        File[] lf = appModel.videoDir.listFiles();
        ArrayList<String> folderPhats = new ArrayList<>();
        int i = 0;
        for (File file :lf){
            folderPhats.add(file.getAbsolutePath());
        }
        for (String path : folderPhats){
            i++;
            if (!event.getList().contains(path)){
                File  toDelete = new File(path);
                try {
                    toDelete.delete();
                    System.out.println("**cleaning files: " + path + "is deleted");
                }catch (Exception e){
                    e.getCause();
                }
            }
        }
    }
}