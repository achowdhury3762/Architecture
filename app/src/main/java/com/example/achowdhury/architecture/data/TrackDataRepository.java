package com.example.achowdhury.architecture.data;

import com.example.achowdhury.architecture.domain.Track;
import com.example.achowdhury.architecture.domain.repository.Artist;

import java.util.List;

import io.reactivex.Observable;

public class TrackDataRepository {

    public Observable<List<Track>> getRelevantTracks(String stringQuery) {
        return null;
    }

    public Observable<List<Track>> getSimilarTracksFromArtist(Artist artist) {
        return null;
    }
}
