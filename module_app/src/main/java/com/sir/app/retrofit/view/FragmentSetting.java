package com.sir.app.retrofit.view;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.sir.app.retrofit.R;
import com.sir.app.retrofit.util.UpdatesUtils;

import org.polaric.colorful.ColorPickerDialog;
import org.polaric.colorful.Colorful;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by zhuyinan on 2017/5/9.
 */

public class FragmentSetting extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    Preference mCachePreference;
    Preference mThemePrimary;
    Preference mThemeAccent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences_setting);
        findPreference(getString(R.string.key_checkUpdates)).setOnPreferenceClickListener(this);
        mThemePrimary = findPreference(getString(R.string.key_theme_primary));
        mThemeAccent = findPreference(getString(R.string.key_theme_accent));
        mThemePrimary.setOnPreferenceClickListener(this);
        mThemeAccent.setOnPreferenceClickListener(this);
        mCachePreference = findPreference(getString(R.string.key_clearCache));
        mCachePreference.setOnPreferenceClickListener(this);
        String cache = getCacheSizeString();
        mCachePreference.setSummary(cache.equals("0.0B") ? "" : cache);
        SwitchPreference switchPref = (SwitchPreference) findPreference(getString(R.string.key_nightMode));
        switchPref.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object obj) {
                        boolean skin = (boolean) obj;
                        Colorful.config(getActivity()).night(skin).apply();
                        mThemePrimary.setEnabled(!skin);
                        mThemeAccent.setEnabled(!skin);
                        restartActivity();
                        return true;
                    }
                });
        if (switchPref.isChecked()) {
            mThemePrimary.setEnabled(false);
            mThemeAccent.setEnabled(false);
        }
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getOrder()) {
            case 1:
                showColorPickerDialog(true);
                break;
            case 2:
                showColorPickerDialog(false);
                break;
            case 3:
                if (!mCachePreference.getSummary().toString().isEmpty()) {
                    clearCache();
                    mCachePreference.setSummary("");
                }
                break;
            case 4:
                new UpdatesUtils().checkUpdates(getActivity());
                break;
        }
        return true;
    }

    private String getCacheSizeString() {
        long size = getFolderSize(new File(getActivity().getCacheDir().getAbsolutePath()));
        return formatFileSize(size);
    }

    private boolean clearCache() {
        return deleteDir(new File(getActivity().getCacheDir().getAbsolutePath()));
    }

    private String formatFileSize(double size) {
        double kByte = size / 1024;
        if (kByte < 1) {
            return size + "B";
        }
        double mByte = kByte / 1024;
        if (mByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gByte = mByte / 1024;
        if (gByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(mByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double tByte = gByte / 1024;
        if (tByte < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(tByte);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    private boolean deleteDir(File dir) {
        if (dir == null || !dir.exists())
            return false;

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private long getFolderSize(File file) {
        if (!file.exists()) {
            return 0;
        }
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public void showColorPickerDialog(final boolean isPrimary) {
        ColorPickerDialog dialog = new ColorPickerDialog(getActivity());
        dialog.setOnColorSelectedListener(new ColorPickerDialog.OnColorSelectedListener() {
            @Override
            public void onColorSelected(Colorful.ThemeColor color) {
                if (isPrimary) {
                    Colorful.config(getActivity()).primaryColor(color).apply();
                } else {
                    Colorful.config(getActivity()).accentColor(color).apply();
                }
                restartActivity();
            }
        });
        dialog.show();
    }

    /**
     * 改变主题颜色重启Activity
     */
    private void restartActivity() {
        getActivity().finish();
        getActivity().startActivity(getActivity().getIntent());
    }
}