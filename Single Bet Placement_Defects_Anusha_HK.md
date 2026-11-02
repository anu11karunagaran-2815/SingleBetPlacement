# Master Defect Log: Sports Betting Application

This document consolidates all identified functional, UI state, data integrity, and filter configuration defects observed during testing.

---

## BUG-001: Incorrect Potential Payout Calculation on Final Success Receipt

* **Severity:** Critical
* **Component:** Receipts & Error Handling (Success Receipt Modal)
* **Business Impact:** Financial Discrepancies & Severe Brand Reputation Risk

### Description
The potential payout value displayed inside the final "Bet Placed Successfully!" receipt modal is mathematically corrupted. While the active Bet Slip correctly calculates the payout as `[Stake × Odds]`, the post-placement success receipt displays an arbitrary lower number.

### Steps to Reproduce
1. Navigate to the application main page.
2. Select any match from the upcoming list by clicking an odds button (e.g., Select **Roma (2)** at **4.20** odds).
3. Verify that the selection populates the right-side fixed Bet Slip.
4. Input a valid stake value into the stake field (e.g., **€10.00**).
5. Verify the active Bet Slip displays the correct potential payout calculation (e.g., €10.00 × 4.20 = **€42.00**).
6. Click the **Place Bet** button.
7. Wait for the asynchronous server response to complete and look at the emerging Success Receipt modal.
8. Inspect the **Potential Payout** data block rendered inside the modal window.

### Results
* **Expected Result:** The potential payout on both the active Bet Slip and the final Success Receipt modal must consistently and accurately reflect the core business formula: `[Stake × Odds]` (e.g., **€42.00**).
* **Actual Result:** The potential payout calculation fails to maintain integrity between views. The active Bet Slip shows **€42.00**, but the final success receipt modal displays an incorrect, disconnected value of **€20.00**.

---

## BUG-002: Delayed Wallet Balance UI Update Post-Placement

* **Severity:** High
* **Component:** Front-End / Wallet UI State Management
* **Business Impact:** High Customer Support Ticket Influx & Regulatory Non-Compliance

### Description
The user profile wallet balance displayed in the global application header fails to dynamically decrement in real time after a bet is successfully placed. The UI retains the stale balance configuration until a manual page reload is performed.

### Steps to Reproduce
1. Log in to the application and note the exact starting wallet balance displayed in the top header.
2. Select an active match from the list to add it to the Bet Slip.
3. Input a valid stake value into the stake field (e.g., **€1.00**).
4. Click the **Place Bet** button.
5. Allow the asynchronous server response to complete and observe the Success Receipt modal.
6. Look at the background wallet balance header while the modal is open, and immediately after clicking **Close**.
7. Perform a manual browser page refresh (**F5** or Hard Refresh) and observe the wallet balance again.

### Results
* **Expected Result:** Immediately upon backend transactional success confirmation, the client-side wallet UI state must dynamically decrement the header balance by the precise stake amount without requiring a reload.
* **Actual Result:** The displayed wallet balance remains completely unchanged (frozen at its pre-placement state) during modal interaction and post-closure. The correct, updated balance is only revealed after a manual browser refresh.

---

## BUG-003: Swapped Team Names and Omitted Match Date on Final Success Receipt

* **Severity:** High
* **Component:** Receipts & Error Handling (Success Receipt Modal)
* **Business Impact:** Legal Auditing Violations & Transaction Chargeback Exposure

### Description
The final success receipt modal breaks data integrity by transposing the structural layout of the competing sports teams (inverting Home vs Away positioning) and completely failing to render the match kickoff date.

### Steps to Reproduce
1. Navigate to the main list view and identify a match with a clear team layout (e.g., **Home: Arsenal**, **Away: Chelsea**).
2. Note the match kickoff date details displayed on the pre-match list card.
3. Select an outcome (e.g., Match Winner: Home) and input a valid stake (e.g., **€1.00**) inside the Bet Slip workspace.
4. Click **Place Bet** and wait for the success confirmation modal to load.
5. Audit the structural team layout string and the timestamp metadata inside the modal window.

### Results
* **Expected Result:** The Success Receipt modal must preserve strict metadata reflection. The team names must mirror the standard structural format: `[Home Team] vs [Away Team]` (Arsenal vs Chelsea), and the match kickoff date must be clearly displayed as a legal proof-of-wager timestamp.
* **Actual Result:** The receipt data block is corrupted. The team names are completely swapped and rendered backwards as `[Away Team] vs [Home Team]` (Chelsea vs Arsenal). Furthermore, the match kickoff date field is completely absent from the modal layout.

---

## BUG-004: Odds Range Filter Fails to Filter Match List Grid

* **Severity:** Medium
* **Component:** Filter Configurations / Match List View
* **Business Impact:** Impaired Content Discoverability & Degraded Conversion Rates

### Description
The range-based Odds Filter component is completely unresponsive. Adjusting the upper and lower constraints fails to trigger an update or restrict the match cards visible in the main feed view.

### Steps to Reproduce
1. Open the application main page to view the populated football match list grid.
2. Locate and open the **Odds Filter** dropdown component.
3. Define a valid range constraint (e.g., **Odds: 1.00 - 3.00**).
4. Apply the range filter parameters.
5. Review all match entries currently rendered in the feed view, specifically auditing the numeric columns under the 1, X, and 2 betting buttons.

### Results
* **Expected Result:** The match list view must instantly filter out all entries that contain no outcomes matching the defined parameter window (e.g., hiding any matches where odds sit entirely outside the 1.00 - 3.00 constraint).
* **Actual Result:** The match list grid remains static and completely unchanged. It continues to display all matches regardless of their odds values (e.g., still showing matches featuring odds parameters as high as **4.10** and **6.00**).

---

## BUG-005: Inaccurate Filter Match Count Total Displayed in Header

* **Severity:** Low
* **Component:** Filter Configurations / UI Metadata
* **Business Impact:** Minor User Experience Friction & UI Polish Erosion

### Description
The total match count indicator badge positioned above the upcoming football match feed displays an inaccurate summary metric that does not synchronize with the actual number of events matching active filter configurations.

### Steps to Reproduce
1. Load the application main page.
2. Observe the total count string displayed in the header element (e.g., "**Showing 103 matches**").
3. Apply a highly restrictive filter parameter (e.g., choosing a specific date like **27/02/2026**).
4. Count the actual number of individual match cards rendered in the resulting refreshed list view below.
5. Compare the physical count against the text indicator summary metric still displayed in the header.

### Results
* **Expected Result:** The total count summary indicator must dynamically match the precise number of individual match item blocks currently rendered and visible to the user.
* **Actual Result:** There is a metadata discrepancy. The header text display remains completely stuck on a high total count ("Showing 103 matches") despite the underlying match grid list filtering down to show only a small subset of matches (e.g., 2 visible match cards).