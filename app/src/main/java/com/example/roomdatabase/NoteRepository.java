package com.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Update;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    public NoteRepository(Application application){
        NoteDatabase database=NoteDatabase.getInstance(application);
        noteDao=database.noteDao();
        allNotes=noteDao.getAllNotes();
    }
    public void insert(Note note){
     new InsetNoteAsynTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsynTask(noteDao).execute(note);
    }

    public void delete(Note note){
    new DeleteNoteAsynTask(noteDao).execute(note);
    }

    public void deleteAllNote(){
      new DeleteAllNoteAsynTask(noteDao).execute();
    }

    public LiveData<List<Note>>  getAllNotes(){
        return allNotes;
    }

  public static class InsetNoteAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
            public InsetNoteAsynTask(NoteDao noteDao){
                  this.noteDao=noteDao;
            }
      @Override
      protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
          return null;
      }
  }
    public static class UpdateNoteAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        public UpdateNoteAsynTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsynTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        public DeleteNoteAsynTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    public static class DeleteAllNoteAsynTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        public DeleteAllNoteAsynTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNote();
            return null;
        }
    }



}
