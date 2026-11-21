import { describe, expect, it } from 'vitest';
import { developmentCatalog } from '../src/features/catalog/data/developmentCatalog';
import { searchTracks } from '../src/features/catalog/searchTracks';

describe('searchTracks', () => {
  it('matches track titles, artists, and styles without case sensitivity', () => {
    expect(searchTracks(developmentCatalog, 'MILA').map((track) => track.id)).toEqual(['demo-01', 'demo-04']);
    expect(searchTracks(developmentCatalog, 'electronic')).toHaveLength(2);
    expect(searchTracks(developmentCatalog, 'paper')).toHaveLength(1);
  });

  it('returns the catalog for a blank query and no results for an unknown query', () => {
    expect(searchTracks(developmentCatalog, '  ')).toBe(developmentCatalog);
    expect(searchTracks(developmentCatalog, 'not-a-track')).toEqual([]);
  });
});