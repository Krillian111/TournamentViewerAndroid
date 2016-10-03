package de.tum.kickercoding.tournamentviewer.setup.monsterdyp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import de.tum.kickercoding.tournamentviewer.tournament.TournamentGamesFragment;
import de.tum.kickercoding.tournamentviewer.tournament.TournamentGamesFragment.OnGameChangeListener;
import de.tum.kickercoding.tournamentviewer.tournament.TournamentStatsFragment;

public class TournamentPagerAdapter extends FragmentPagerAdapter implements OnGameChangeListener {

	private final static int NUM_PAGES = 2;

	private final static int PAGE_STATS = 0;
	private final static int PAGE_GAMES = 1;

	public final static String PAGER_HEADER_STATS = "Stats";
	public final static String PAGER_HEADER_GAMES = "Games";

	private TournamentStatsFragment tab_stats;


	public TournamentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return NUM_PAGES;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case PAGE_GAMES: {
				return new TournamentGamesFragment();
			}
			case PAGE_STATS:
			default: {
				tab_stats = new TournamentStatsFragment();
				return tab_stats;
			}
		}
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case PAGE_GAMES: {
				return PAGER_HEADER_GAMES;
			}
			case PAGE_STATS:
			default: {
				return PAGER_HEADER_STATS;
			}
		}
	}


	public void onGameChanged() {
		tab_stats.onGameChanged();
	}
}