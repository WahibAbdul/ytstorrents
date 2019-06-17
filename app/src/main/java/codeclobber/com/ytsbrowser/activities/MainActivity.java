package codeclobber.com.ytsbrowser.activities;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

import java.util.ArrayList;
import java.util.List;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.YTSApp;
import codeclobber.com.ytsbrowser.adapters.RVLeftMenuAdapter;
import codeclobber.com.ytsbrowser.constants.Constant;
import codeclobber.com.ytsbrowser.fragments.BaseFragment;
import codeclobber.com.ytsbrowser.fragments.BrowseAllFragment;
import codeclobber.com.ytsbrowser.fragments.HomeFragment;
import codeclobber.com.ytsbrowser.fragments.SettingsFragment;
import codeclobber.com.ytsbrowser.fragments.TopRatedFragment;
import codeclobber.com.ytsbrowser.inapppurchaseutils.Base64;
import codeclobber.com.ytsbrowser.inapppurchaseutils.Base64DecoderException;
import codeclobber.com.ytsbrowser.inapppurchaseutils.IabHelper;
import codeclobber.com.ytsbrowser.inapppurchaseutils.IabResult;
import codeclobber.com.ytsbrowser.inapppurchaseutils.Inventory;
import codeclobber.com.ytsbrowser.inapppurchaseutils.Purchase;
import codeclobber.com.ytsbrowser.interfaces.ActivityDrawerCallback;
import codeclobber.com.ytsbrowser.interfaces.NavMenuClickCallback;
import codeclobber.com.ytsbrowser.utils.UIUtil;

public class MainActivity extends AppCompatActivity implements NavMenuClickCallback, ActivityDrawerCallback {

    static final String SKU_PREMIUM = "com.codeclobber.yts.premiumupgrade";

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private BaseFragment mCurrentFragment;
    private IabHelper mIabHelper;
    private IInAppBillingService mService;
    private ServiceConnection mServiceConn;

    private int mSelectedMenuIndex = 0;

    private int[] mMenuNames = {
            R.string.home, R.string.top_rated, R.string.browse_all, R.string.settings,
            R.string.exit
    };
    private int[] mMenuIcons = {
            R.drawable.ic_home,
            R.drawable.ic_star_white,
            R.drawable.ic_browse_all,
            R.drawable.ic_settings,
            R.drawable.ic_exit,
    };
    private String mPremiumUpgradePrice = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setBackgroundDrawable(null);

        initViews();
        setupToolbar();
        setupDrawerLayout();
        setupStartFragment();

//         Subscribe to Android Topic on Firebase
        String topic = Constant.NotificationTopic.TESTING;
        if (YTSApp.IS_LIVE_ENVIRONMENT) topic = Constant.NotificationTopic.LIVE;


//        setupInAppBilling();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Perform Final Search
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Text has changed
//                return true;
//            }
//        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIabHelper != null)
            mIabHelper.dispose();
        mIabHelper = null;
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }

    // MARK: Helper Methods

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        RecyclerView mMenuRecyclerView = (RecyclerView) findViewById(R.id.rv_Menu);

        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMenuRecyclerView.setAdapter(new RVLeftMenuAdapter(this, mMenuNames, mMenuIcons, this));
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(R.string.app_name);
            setSupportActionBar(mToolbar);
        }
    }

    private void setupDrawerLayout() {

        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string
                .close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(drawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

    }

    private void setupStartFragment() {
        mCurrentFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ll_fragments_view, mCurrentFragment)
                .commit();
    }

    public void changeFragment(int index) {
        switch (index) {
            case 0:
                mCurrentFragment = new HomeFragment();
                UIUtil.toolbarSetTitle(getSupportActionBar(), getString(R.string.app_name));
                break;
            case 1:
                mCurrentFragment = new TopRatedFragment();
                UIUtil.toolbarSetTitle(getSupportActionBar(), getString(R.string.top_rated));
                break;
            case 2:
                mCurrentFragment = new BrowseAllFragment();
                UIUtil.toolbarSetTitle(getSupportActionBar(), getString(R.string.browse_all));
                break;
            case 3:
                mCurrentFragment = new SettingsFragment();
//                UIUtil.toolbarSetShadow(this, mToolbar, R.dimen.toolbar_elevation);
                UIUtil.toolbarSetTitle(getSupportActionBar(), getString(R.string.settings));
                break;
            case 4:
                finish();
            default:
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_fragments_view, mCurrentFragment)
                .commit();
        closeDrawer();
    }

    private void setupInAppBilling() {
        String base64EncodedPublicKey = getString(R.string.base64_in_app_key);
        String decodedString;
        try {
            decodedString = new String(Base64.decode(base64EncodedPublicKey.getBytes()));
        } catch (Base64DecoderException e) {
            e.printStackTrace();
            decodedString = "";
        }

        mIabHelper = new IabHelper(this, decodedString);

        mIabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Problem connect with Google In-App Billing
                    Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // In-App billing is setup
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    if (mIabHelper == null) return;

                    List<String> additionalSkuList = new ArrayList<>();
                    additionalSkuList.add(SKU_PREMIUM);
                    mIabHelper.queryInventoryAsync(true, additionalSkuList,
                            new IabHelper.QueryInventoryFinishedListener() {
                                @Override
                                public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                                    if (result.isFailure() || mIabHelper == null) {
                                        return;
                                    }
                                    mPremiumUpgradePrice = inv.getSkuDetails(SKU_PREMIUM).getPrice();
                                    Toast.makeText(MainActivity.this, mPremiumUpgradePrice, Toast.LENGTH_SHORT).show();

                                    mIabHelper.launchPurchaseFlow(MainActivity.this, SKU_PREMIUM, 10001,
                                            new IabHelper.OnIabPurchaseFinishedListener() {
                                                @Override
                                                public void onIabPurchaseFinished(IabResult result, Purchase info) {
                                                    if (result.isFailure() || mIabHelper == null) {
                                                        return;
                                                    } else if (info.getSku().equals(SKU_PREMIUM)) {
                                                        // consume the gas and update the UI
                                                        Toast.makeText(MainActivity.this, "Upgrade to premium", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
                                }
                            });


                }
            }
        });

//        mServiceConn = new ServiceConnection() {
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                mService = null;
//            }
//
//            @Override
//            public void onServiceConnected(ComponentName name,
//                                           IBinder service) {
//                mService = IInAppBillingService.Stub.asInterface(service);
//                Intent serviceIntent =
//                        new Intent("com.android.vending.billing.InAppBillingService.BIND");
//                serviceIntent.setPackage("com.android.vending");
//                bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
//
//                if (mService != null) {
//                    new GetSKUDetail().execute();
//                }
//            }
//        };

    }


    // MARK: ActivityDrawerCallbacks
    @Override
    public void openDrawer() {
        if (mDrawerLayout != null && mNavigationView != null) {
            mDrawerLayout.openDrawer(mNavigationView);
        }
    }

    @Override
    public void closeDrawer() {
        if (mDrawerLayout != null && mNavigationView != null) {
            mDrawerLayout.closeDrawer(mNavigationView);
        }
    }

    // MARK: NavMenuClickCallback
    @Override
    public void itemClick(int index) {
        if (mSelectedMenuIndex != index) {
            mSelectedMenuIndex = index;
            changeFragment(index);
        }
    }

}
