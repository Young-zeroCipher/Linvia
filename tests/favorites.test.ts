import { beforeEach, describe, expect, it } from 'vitest';
import { useFavoritesStore } from '../src/features/library/store/favorites';

describe('favorites store', () => {
  beforeEach(() => {
    useFavoritesStore.setState({ trackIds: [] });
  });

  it('adds and removes a saved track when toggled', () => {
    useFavoritesStore.getState().toggleTrack('demo-01');
    expect(useFavoritesStore.getState().trackIds).toEqual(['demo-01']);

    useFavoritesStore.getState().toggleTrack('demo-01');
    expect(useFavoritesStore.getState().trackIds).toEqual([]);
  });
});