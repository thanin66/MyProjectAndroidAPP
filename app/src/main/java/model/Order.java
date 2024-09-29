package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {

    private int id; // ID attribute
    private String name;
    private String processor;
    private String processorimage;
    private String vga;
    private String vgaimage;
    private String ram;
    private String storage;

    // Constructor
    public Order(int id, String name, String processor,String processorimage, String vga, String vgaimage, String ram, String storage) {
        this.id = id;
        this.name = name;
        this.processor = processor;
        this.processorimage = processorimage;
        this.vga = vga;
        this.vgaimage = vgaimage;
        this.ram = ram;
        this.storage = storage;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getters for other attributes
    public String getName() {
        return name;
    }

    public String getProcessor() {
        return processor;
    }

    public String getProcessorImage() {
        return processorimage;
    }

    public String getVga() {
        return vga;
    }

    public String getVgaImage() {
        return vgaimage;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    // Parcelable implementation
    protected Order(Parcel in) {
        id = in.readInt();
        name = in.readString();
        processor = in.readString();
        processorimage = in.readString();
        vga = in.readString();
        vgaimage = in.readString();
        ram = in.readString();
        storage = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(processor);
        dest.writeString(processorimage);
        dest.writeString(vga);
        dest.writeString(vgaimage);
        dest.writeString(ram);
        dest.writeString(storage);
    }
}
