package com.example.a7minuteworkout

class Constants{
    companion object exerciseList {
        fun getExerciseList(): ArrayList<Exercise>{
            val result: ArrayList<Exercise> = ArrayList()
            result.add(Exercise(1,"Jumping jacks",R.drawable.exercise_jumping_jacks,false,false))
            result.add(Exercise(2,"Wall Sit",R.drawable.exercise_wall_sit,false,false))
//            result.add(Exercise(3,"Push Up",R.drawable.exercise_push_up,false,false))
//            result.add(Exercise(4,"Abdominal Crunch",R.drawable.exercise_abdominal_crunch,false,false))
//            result.add(Exercise(5,"Step-Up onto Chair",R.drawable.exercise_set_up_onto_chair,false,false))
//            result.add(Exercise(6,"Squat",R.drawable.exercise_squats,false,false))
//            result.add(Exercise(7,"Triceps Dip On Chair",R.drawable.exercise_triceps_dip_on_chair,false,false))
//            result.add(Exercise(8,"Plank",R.drawable.exercise_plank,false,false))
//            result.add(Exercise(9,"High Knees Running In Place",R.drawable.exercise_high_knees_running_in_place,false,false))
//            result.add(Exercise(10,"Lunges",R.drawable.exercise_lunge,false,false))
//            result.add(Exercise(11,"Push up and Rotation",R.drawable.exercise_push_up_with_rotation,false,false))
//            result.add(Exercise(12,"Side Plank",R.drawable.exercise_side_plank,false,false))
            return result
        }
    }
}