import type { Track } from './types';

export function searchTracks(tracks: Track[], query: string): Track[] {
  const normalizedQuery = query.trim().toLocaleLowerCase();
  if (!normalizedQuery) return tracks;

  return tracks.filter(({ title, artist, style }) =>
    `${title} ${artist} ${style}`.toLocaleLowerCase().includes(normalizedQuery),
  );
}