package com.mybareskinph.theBareskinApp.home.views;

import com.mybareskinph.theBareskinApp.base.BaseFragment;

/**
 * Created by paulolosbanos on 8/11/17.
 */

public class FormFragments extends BaseFragment {

    private boolean answered = false;

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isAnswered() {
        return answered;
    }

}
