package tests.models;

import com.google.gson.annotations.SerializedName;

public class GlossaryModel {
    private String title;
    @SerializedName("gloss_div")
    private GlossDivModel glossDiv;

    public String getTitle() {
        return title;
    }



    public GlossDivModel getGlossDiv() {
        return glossDiv;
    }




    public static class GlossDivModel{
        private String title;
        private boolean flag;

        public String getTitle() {
            return title;
        }

        public boolean isFlag() {
            return flag;
        }

    }


}


