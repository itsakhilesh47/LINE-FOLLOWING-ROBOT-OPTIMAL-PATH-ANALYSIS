import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * A simple GUI window that displays only the algorithm analysis results.
 * All other functionality remains in the terminal interface.
 */
public class AnalysisViewer extends JFrame {
    private DefaultTableModel tableModel;
    private JTextArea pathDetailsArea;
    
    public AnalysisViewer() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Algorithm Analysis Results");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Just close the window, not the whole application
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Results table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Algorithm Comparison"));
        
        // Table for results
        String[] columnNames = {"Algorithm", "Execution Time (ms)", "Path Length", "Nodes Explored", 
                               "Time Complexity", "Space Complexity", "Explanation"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(40);
        resultTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        resultTable.getColumnModel().getColumn(6).setPreferredWidth(250);
        
        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Path details panel
        JPanel pathPanel = new JPanel(new BorderLayout());
        pathPanel.setBorder(BorderFactory.createTitledBorder("Path Details"));
        
        pathDetailsArea = new JTextArea(6, 50);
        pathDetailsArea.setEditable(false);
        JScrollPane pathScrollPane = new JScrollPane(pathDetailsArea);
        pathPanel.add(pathScrollPane, BorderLayout.CENTER);
        
        mainPanel.add(pathPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Display algorithm analysis results in the GUI
     */
    public void displayResults(PathResult bfsResult, long bfsDuration,
                              PathResult dfsResult, long dfsDuration,
                              PathResult dijkstraResult, long dijkstraDuration,
                              PathResult aStarResult, long aStarDuration) {
        
        // Clear previous results
        tableModel.setRowCount(0);
        
        // Add results to table
        tableModel.addRow(new Object[]{"BFS", bfsDuration, bfsResult.getPathLength(), bfsResult.getNodesExplored(), 
                                      "O(V + E)", "O(V)", "Uses queue, finds shortest paths by number of edges"});
        
        tableModel.addRow(new Object[]{"DFS", dfsDuration, dfsResult.getPathLength(), dfsResult.getNodesExplored(), 
                                      "O(V + E)", "O(V)", "Uses recursion/stack, good for deep paths but not optimal"});
        
        tableModel.addRow(new Object[]{"Dijkstra", dijkstraDuration, dijkstraResult.getPathLength(), dijkstraResult.getNodesExplored(), 
                                      "O(E log V)", "O(V)", "Uses priority queue, finds shortest weighted path"});
        
        tableModel.addRow(new Object[]{"Kruskal", aStarDuration, aStarResult.getPathLength(), aStarResult.getNodesExplored(), 
                                      "O(E log V)", "O(V)", "Finds Minimum Spanning Tree and adapts to path-finding"});
        
        // Update path details
        StringBuilder sb = new StringBuilder();
        sb.append("BFS Path: ").append(bfsResult.getPath()).append("\n");
        sb.append("DFS Path: ").append(dfsResult.getPath()).append("\n");
        sb.append("Dijkstra Path: ").append(dijkstraResult.getPath()).append("\n");
        sb.append("Kruskal Path: ").append(aStarResult.getPath()).append("\n");
        pathDetailsArea.setText(sb.toString());
        
        // Make window visible if it's not already
        if (!isVisible()) {
            setVisible(true);
        }
    }
} 