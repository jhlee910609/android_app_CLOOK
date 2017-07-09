// Generated code from Butter Knife. Do not modify!
package com.example.junhee.weatherparse;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.tv_temp = Utils.findRequiredViewAsType(source, R.id.tv_temp, "field 'tv_temp'", TextView.class);
    target.tv_wind = Utils.findRequiredViewAsType(source, R.id.tv_wind, "field 'tv_wind'", TextView.class);
    target.tv_humid = Utils.findRequiredViewAsType(source, R.id.tv_humid, "field 'tv_humid'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_temp = null;
    target.tv_wind = null;
    target.tv_humid = null;
  }
}
