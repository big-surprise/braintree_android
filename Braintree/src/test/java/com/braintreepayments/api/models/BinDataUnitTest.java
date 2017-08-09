package com.braintreepayments.api.models;

import android.os.Parcel;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static com.braintreepayments.api.models.BinData.UNKNOWN;
import static com.braintreepayments.testutils.FixturesHelper.stringFromFixture;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class BinDataUnitTest {

    @Test
    public void parsesCorrectly_ifEmptyJson() throws JSONException {
        BinData binData = BinData.fromJson(new JSONObject("{}"));
        assertNotNull(binData);
        assertEquals(UNKNOWN, binData.getPrepaid());
        assertEquals(UNKNOWN, binData.getHealthcare());
        assertEquals(UNKNOWN, binData.getDebit());
        assertEquals(UNKNOWN, binData.getDurbinRegulated());
        assertEquals(UNKNOWN, binData.getCommercial());
        assertEquals(UNKNOWN, binData.getPayroll());
        assertEquals(UNKNOWN, binData.getIssuingBank());
        assertEquals(UNKNOWN, binData.getCountryOfIssuance());
        assertEquals(UNKNOWN, binData.getProductId());
    }

    @Test
    public void parsesCorrectly_ifNull() throws JSONException {
        BinData binData = BinData.fromJson(new JSONObject());
        assertNotNull(binData);
        assertEquals(UNKNOWN, binData.getPrepaid());
        assertEquals(UNKNOWN, binData.getHealthcare());
        assertEquals(UNKNOWN, binData.getDebit());
        assertEquals(UNKNOWN, binData.getDurbinRegulated());
        assertEquals(UNKNOWN, binData.getCommercial());
        assertEquals(UNKNOWN, binData.getPayroll());
        assertEquals(UNKNOWN, binData.getIssuingBank());
        assertEquals(UNKNOWN, binData.getCountryOfIssuance());
        assertEquals(UNKNOWN, binData.getProductId());
    }

    @Test
    public void isParcelable() throws JSONException {
        BinData binData = BinData.fromJson(new JSONObject(stringFromFixture("bin_data.json")));
        Parcel parcel = Parcel.obtain();
        binData.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        BinData parceled = BinData.CREATOR.createFromParcel(parcel);
        assertBinData(binData, parceled);
    }

    public static void assertBinData(BinData expected, BinData actual) {
        assertEquals(expected.getPrepaid(), actual.getPrepaid());
        assertEquals(expected.getHealthcare(), actual.getHealthcare());
        assertEquals(expected.getDebit(), actual.getDebit());
        assertEquals(expected.getDurbinRegulated(), actual.getDurbinRegulated());
        assertEquals(expected.getCommercial(), actual.getCommercial());
        assertEquals(expected.getPayroll(), actual.getPayroll());
        assertEquals(expected.getIssuingBank(), actual.getIssuingBank());
        assertEquals(expected.getCountryOfIssuance(), actual.getCountryOfIssuance());
        assertEquals(expected.getProductId(), actual.getProductId());
    }

}