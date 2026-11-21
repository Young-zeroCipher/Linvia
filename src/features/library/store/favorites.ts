import { create } from 'zustand';

type FavoritesState = {
  trackIds: string[];
  toggleTrack: (trackId: string) => void;
};

export const useFavoritesStore = create<FavoritesState>((set) => ({
  trackIds: [],
  toggleTrack: (trackId) => set((state) => ({
    trackIds: state.trackIds.includes(trackId)
      ? state.trackIds.filter((id) => id !== trackId)
      : [...state.trackIds, trackId],
  })),
}));