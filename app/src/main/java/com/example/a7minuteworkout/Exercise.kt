package com.example.a7minuteworkout

class Exercise(
    private var ID:Int,
    private var Name:String,
    private var Image:Int,
    private var isFinish:Boolean,
    private var isSelected:Boolean){

//    fun setID(ID: Int){
//        this.ID = ID
//    }
//
//    fun setName(Name: String){
//        this.Name = Name
//    }
//
//    fun setImage(Image: Int){
//        this.Image = Image
//    }

    fun setIsFinish(isFinish: Boolean){
        this.isFinish = isFinish
    }

    fun setIsSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }

    fun getID():Int = this.ID
    fun getName():String = this.Name
    fun getImage():Int = this.Image
    fun getIsFinish():Boolean = this.isFinish
    fun getIsSelected():Boolean = this.isSelected
}