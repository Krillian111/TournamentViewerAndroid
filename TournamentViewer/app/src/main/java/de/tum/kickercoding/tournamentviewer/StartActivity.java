package de.tum.kickercoding.tournamentviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.tum.kickercoding.tournamentviewer.exceptions.AppManagerException;
import de.tum.kickercoding.tournamentviewer.manager.AppManager;
import de.tum.kickercoding.tournamentviewer.modes.monsterdyp.MonsterDypSetupActivity;

/**
 * Activity is called when app is opened, responsible for initializing basic infrastructure
 * Presents the user with a screen of basic options
 */
// TODO: add possibility to load pending tournament (click on monsterdyp calls initialization of TournamentManager which resets the Tournament
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        try {
			initializeApp();
		} catch (AppManagerException e) {
			AppManager.getInstance().displayError(this, "Critical Error during startup, please restart");
		}
    }

    /** Called when the user clicks the MonsterDYP button */
    public void monsterDypSetup(View view) {
        AppManager.getInstance().initializeTournamentManager();
        Intent intent = new Intent(this, MonsterDypSetupActivity.class);
        startActivity(intent);
    }

    private void initializeApp() throws AppManagerException {
		AppManager.getInstance().initialize(getApplicationContext());
    }
}
