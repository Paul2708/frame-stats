# Frame-Stats
## Sample configuration
```yaml
locations:
  left-lower-corner:
    ==: org.bukkit.Location
    world: world
    x: -304
    y: 76
    z: 164
    pitch: 0.0
    yaw: 0.0
  right-upper-corner:
    ==: org.bukkit.Location
    world: world
    x: -304
    y: 78
    z: 160
    pitch: 0.0
    yaw: 0.0
columns:
- ==: Column
  max-width: 30
  name: '#'
  index: 0
- ==: Column
  max-width: 128
  name: 'Spieler'
  index: 1
- ==: Column
  max-width: 64
  name: 'Kills'
  index: 2
- ==: Column
  max-width: 64
  name: 'Tode'
  index: 3
- ==: Column
  max-width: 64
  name: 'Punkte'
  index: 4
sizes:
  row-height: 20
rows: 10
background-image: 'background.jpg'
search-button:
  ==: Search-Button
  x: 10
  y: 10
  width: 160
  height: 20
```
